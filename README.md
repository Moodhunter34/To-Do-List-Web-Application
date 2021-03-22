Coverage: src/main/java 93%

# Project Title
To-do List Web Application(TDL)

# Introduction

This repository contains code for the Todo- List Project List for QA.

## How to run the application without compiling to a jar file

### Using the supplied mvnw Maven wrapper

```
./mvnw spring-boot:run
```

### Using a local installation of maven

```
mvn spring-boot:run
```

## How to package to a jar file and run the jar file

```
// Uses the maven compiler plugin supplied with a Spring Boot project
mvn package

// Runs the jar file using your local installation of Java
java -jar target/To-Do-List-Web-Application-0.0.0-SNAPSHOT.java
```




## What is a Spring Bean

A Spring Bean is simply a managed object, managed by Spring that is... This is called Inversion of Control and also makes the object a candidate for dependency injection. Spring Beans' are stored in the `Application Context`.

We can register a bean in multiple ways, the simplest is to create a configuration class that holds our bean definitions:

```
@Configuration // Registers the class as a bean, this is a specialised bean that is used for providing beans from our bean definitions (methods in this class)
public class Config {

  @Bean // Registers a method as a bean here
  @Scope("prototype") // A new object is generated everytime we call this bean
  public String getSomething() {
    return "Hello World!";
  }
}
```

As the configuration class is registered to Spring with `@Configuration`, it will be scanned during the component scan (other beans will be picked up as when correctly annotated) that occurs when your Spring Boot app is bootstrapping itself.

We can also apply `@Component` to a class to indicate it should be registered as a bean, but not for supplying bean definitions. Instead, it is used for beans that provide behaviour through their methods. We also have specialisations of this annotation such as `@Service` or `@Repository`.

## What is @SpringBootApp

`@SpringBootApp` is a convenience annotation that will do multiple different things, including bootstrapping the application with a pre-made configuration where possible. THIS MUST BE APPLIED TO THE CLASS THAT CONTAINS YOUR `main` METHOD! One of the annotations that `@SpringBootApp` will run is the `@ComponentScan` annotation. This will recursively scan from the current package, and all sub-packages, for any classes labelled as beans and add them to the Application Context.

```
@SpringBootApplication
public class ToDoListWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ToDoListWebApplication.class, args);
	}

}
```


Our properties are supplied in the form of key-value pairs:

```
server.port=8080
```

This property changes the port.

### Multiple property files


The root property file, `application.properties`, will always be acknowledged by Spring Boot. Any properties file that begins with `application`, and is suffixed with a `-<name>.properties`, will be available for selection. This allows us to have production and test configurations for our applications.

```
spring.profiles.active=test
```

The `spring.profiles.active` property has been set to `test`, this means it will select the property file called `application-test.properties`.

## Dependency Injection

Spring is an Inversion of Control container, and it manages beans in the application context. We know we can register those beans, but how can we retrieve them (have the dependency injected). We can trigger dependency injection in Spring apps with the `@Autowired` annotation:

```
@Service
public class UserService {

}

@RestController
public class UserController {

  // We could use @Autowired here as well, this would then be field injection
  private UserService userService;
  
  @Autowired // Using constructor injection to request the `UserService` Service bean from the application context when creating this object
  public UserController(UserService userService) {
    this.userService = userService;
  }
}
```

## What is a Controller

A controller can mean many things - in Spring, we can refer to it as a bean that handles an incoming request and returns a response in return (usually after some form of business logic has been executed). The controller will likely make requests to a service to enact the business logic.

A basic controller can be defined using the `@Controller` annotation, but by itself it cannot return JSON data - Spring needs to know whether you want to return a response body of data or not. We can do this by applying `@ResponseBody` to a our methods:

```
@Controller
public class UserController {

	@GetMapping("/user")
	@ResponseBody
	public UserDTO returnSomething() {
		return new UserDTO(2, "Nikos", "Pap", "nikospap8");
	}
	
}
```

A simplification of this process is to use the `@RestController` annotation, which will tell Spring our controller methods can support the serialization and deserialization of objects to send in the body of a response.

- *Serialization* is when an object is transformed into JSON, or some other data format...
- *Deserialization* is when some JSON, or other data format, is transformed back into its object form...

```
@RestController
@RequestMapping(path = "/todo")
public class TodoController {
	
	private TodoService todoService;
	
	@Autowired // constructor injection
	public todoController(TodoService todoService) {
		this.tosoService = todoService;
	}
	
	// localhost:8080/todo
	@GetMapping
	public ResponseEntity<List<TodoDTO>> getAllTodos() {
		
		// Response has headers, a body and a status code
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "2");
		
		List<TodoDTO> data = todoService.readAllTodos();
		
		// ResponseEntity(Body, Headers, HttpStatus)
		return new ResponseEntity<List<TodoDTO>>(data, httpHeaders, HttpStatus.OK);
	}
}
```

### Request Mappings

Spring Web supplies us with many different annotations we can use on our controllers, the most commonly seen are:

- `@PatchMapping`
- `@GetMapping`
- `@DeleteMapping`
- `@PostMapping`



These mappings correspond to a specific type of HTTP request.

### @RequestParam, @PathVariable, @PathParam

`@PathParam` is excluded from these examples, `@RequestParam` is a more powerful version that is implemented by Spring Boot.

The `@RequestParam` annotation allows us to get query parameters from requests url, a query parameter looks like `?id=1` and can be chained with amphersands `?id=1&name=nikos`.

The `@PathVariable` annotation allows us to get variables from a url path. Specify a path variable with `/{somePathVariableName}` within your URLs path.

```


## What is a Service

A service is responsible for implementing business logic, we can indicate a service to be registered as a bean using the `@Service` annotation. This annotation is a specialisation of `@Component` which also registers a component as a Spring bean.

```
@Service // labelled as a bean (managed by Spring)
public class UserService {
	
	// Data Access Object
	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	@Autowired
	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<UserDTO> readAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		userDTOs = users.stream().map(userMapper::mapToDTO).collect(Collectors.toList());
		
		return userDTOs;
	}
}
```

## What is a Repository

A repository in Spring Boot is a Data Access Object (DAO) that can be registered as a bean using the `@Repository` annotation. Repositories can be supplied with some basic CRUD functionality thanks to the Spring Data JPA, Hibernate and the JDBC, this saves a lot of time as we no longer have to focus on mapping objects to table data and vice-versa.

To create a standard, database-independent repository, we should extend from the `JpaRepository` interface within our repository interface. We use an interface as we don't provide the database connection implementation ourselves, Spring Data JPA and Hibernate provide the implementation.

```
// The type specified, User, is the type of data that this repository will be accessing
// - It is able to do this as the User model class is registered as an Entity
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
```

## What is a Entity model

An Entity model in Spring is used by the JPA implementation (Hibernate) to create database tables and generate SQL to run against our database using the JDBC driver implementation. We can signal an entity with the `@Entity` annotation, and apply various annotations for validation (database constraints). The entity model is usually the business domain model:

```
@Entity // JPA + Hibernate will auto-create table for this class
public class User {

	@Id // Auto-incrementing
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "firstName")
	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	private String userName;

	private String password;
  
}
```

We can also have our validation rules applied before our service or repository are ever reached, which means hibernate never needs to run and waste processing power. We do this with the `@Valid` annotation applied to a controller methods parameter.

```
@PostMapping
public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
  UserDTO newUser = userService.createUser(user);

  HttpHeaders headers = new HttpHeaders();
  headers.add("Location", String.valueOf(newUser.getId()));

  return new ResponseEntity<UserDTO>(newDuck, headers, HttpStatus.CREATED);
}
```


## What is the JPA?

The JPA (Java Persistence API) is a Java EE specification that defines rules, and more, around how an ORM (Object Relational Mapper) should map object data in Java to and from native SQL table schemas and data. In a Spring Boot project, we will often use the Spring Data JPA as it is a more powerful version of the original JPA. The Spring Data JPA will handle a lot of the JDBC configuration and mapping for us (when given an implementation).

## What is Hibernate?

Hibernate is an ORM (Object Relational Mapper) that can map Java objects to SQL table schemas and data and vice-versa. Hibernate is actually an implementation of the JPA and is the intermediary between our objects and JDBC.

## What is JDBC?

JDBC is a specification for accessing, creating, modifying and deleting (and more) SQL data using Java. The JDBC api must be supplied with a specific driver (implementation) to be able to communicate with a database.

## Exception Handling

Custom exceptions can be created by extending `Exception`:

```
class MyCustomException extends Exception {}
```

We can also extend other exceptions for better type specificity:

```
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A user was not found")
public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException() {
		super(); // calls EntityNotFoundException()
	}

	public UserNotFoundException(String message) {
		super(message); // calls EntityNotFoundException(String message)
	}
}
```

The above `UserNotFoundException` is a custom exception inheriting from the `EntityNotFoundException`, we can throw this in our code like any other exception. We can also specify a simple response by annotating the exception with `@ResponseStatus`. The above example states:

- The response code is a 404, using the enum value of HttpStatus.NOT_FOUND
- The reason/message sent in the response is the supplied String literal

## DTOs

A DTO (Data Transfer Object) provides a way of returning sanitised, or more specific, data from an API. 

- A DTO will match our domain entity models fields/state, or may not match our domain model at all and instead uses data retrieved from the domain model to process into new data for our DTO.

Example domain entity:

```
@Entity
public class SimpleEntityModel {

  @Id
  private int id;
  
  private String name;
  
  private String privateData;
  
}
```

Example domain DTO:

```
// We don't have privateData on our DTO
public class SimpleEntityModelDTO {

  private int id;
  
  private String name;
    
}
```

Unit Tests
Every file needs to have it's own respective test file and test the methods* within. We make sure we have the correct imports on top.



These tests, test the constructors and the methods in the main file. This happends with dummy values and with assertions. They are several types of assetions, but the main ones are assertEquals, assertNotNull, assertSame, assertTrue.


Integration Tests
Explain what these tests test, why and how to run them

These tests, have an approach to target the fundamental buidling blocks of an application. We can use Mockito for Integration Testing, using mock values to see if it works. We always make sure we have imported the correct imports on top.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
* **Nikolaos Papadopoulos** - [Moodhunter34](https://github.com/Moodhunter34)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Jordan Harrison** - *Other Contributors* - [JHarry444](https://github.com/JHarry444)
* **Ed Reynolds** - *Other Contributors* - [Erdz-96](https://github.com/Edrz-96)
* **Morgan Walsh** - *Other Contributors* - [MrWalshyType2](https://github.com/MrWalshyType2)


* Hat tip to anyone whose code was used
* Inspiration
* etc

