# Introduction

This repository contains code used for the delivery of Spring Boot for the 21JANSDET2 cohort.

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
java -jar target/duck-demo-0.0.0-SNAPSHOT.java
```

## Installing Spring Boot Tool Suite using the Eclipse Marketplace

1. Navigate to the Eclipse Marketplace

![image](https://user-images.githubusercontent.com/67016030/110522062-69390c80-8108-11eb-9e7d-e8fbfd56a0dc.png)

2. Search for 'Spring Tools Suite', install the relevant version

![image](https://user-images.githubusercontent.com/67016030/110522342-b2895c00-8108-11eb-8f95-5e0ec832b5bb.png)

## Creating a new Spring Boot project

1. Navigate to `File -> New -> Other`

![image](https://user-images.githubusercontent.com/67016030/110522797-393e3900-8109-11eb-960e-d70c126e3ea8.png)


2. Search for `Spring` and select `Spring Starter Project`

![image](https://user-images.githubusercontent.com/67016030/110522858-49561880-8109-11eb-90f4-7dcfa5d5b887.png)

3. Configure your project settings

![image](https://user-images.githubusercontent.com/67016030/110522968-6db1f500-8109-11eb-9aa6-df0cb7c7d901.png)

4. Select any dependencies you might need, the following selected in the image are good for a simple REST API

![image](https://user-images.githubusercontent.com/67016030/110523205-b23d9080-8109-11eb-83a6-b23407cd3c24.png)

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
public class DuckDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DuckDemoApplication.class, args);
	}

}
```

## The application.properties file

The `application.properties` file is a configuration file that we can use to customise the pre-defined configurations supplied by Spring + Spring Boot. The file is located in the `src/main/resources` source package.

![image](https://user-images.githubusercontent.com/67016030/110528691-56c2d100-8110-11eb-8c7a-099c91c8ae3c.png)

Our properties are supplied in the form of key-value pairs:

```
server.port=8080
```

This property changes the port.

### Multiple property files

As you may have seen in the prior image, there are multiple property files.

![image](https://user-images.githubusercontent.com/67016030/110528703-588c9480-8110-11eb-9b86-2043199a90f3.png)

The root property file, `application.properties`, will always be acknowledged by Spring Boot. Any properties file that begins with `application`, and is suffixed with a `-<name>.properties`, will be available for selection. This allows us to have production and test configurations for our applications.

```
spring.profiles.active=test
```

The `spring.profiles.active` property has been set to `test`, this means it will select the property file called `application-test.properties`.

## Dependency Injection

Spring is an Inversion of Control container, and it manages beans in the application context. We know we can register those beans, but how can we retrieve them (have the dependency injected). We can trigger dependency injection in Spring apps with the `@Autowired` annotation:

```
@Service
public class SomeService {

}

@RestController
public class SomeController {

  // We could use @Autowired here as well, this would then be field injection
  private SomeService someService;
  
  @Autowired // Using constructor injection to request the `SomeService` Service bean from the application context when creating this object
  public SomeController(SomeService someService) {
    this.someService = someService;
  }
}
```

## What is a Controller

A controller can mean many things - in Spring, we can refer to it as a bean that handles an incoming request and returns a response in return (usually after some form of business logic has been executed). The controller will likely make requests to a service to enact the business logic.

A basic controller can be defined using the `@Controller` annotation, but by itself it cannot return JSON data - Spring needs to know whether you want to return a response body of data or not. We can do this by applying `@ResponseBody` to a our methods:

```
@Controller
public class BaseController {

	@GetMapping("/test")
	@ResponseBody
	public DuckDTO returnSomething() {
		return new DuckDTO(23, "Fred", "Red", "Beach");
	}
	
}
```

A simplification of this process is to use the `@RestController` annotation, which will tell Spring our controller methods can support the serialization and deserialization of objects to send in the body of a response.

- *Serialization* is when an object is transformed into JSON, or some other data format...
- *Deserialization* is when some JSON, or other data format, is transformed back into its object form...

```
@RestController
@RequestMapping(path = "/duck")
public class DuckController {
	
	private DuckService duckService;
	
	@Autowired // constructor injection
	public DuckController(DuckService duckService) {
		this.duckService = duckService;
	}
	
	// localhost:8080/duck
	@GetMapping
	public ResponseEntity<List<DuckDTO>> getAllDucks() {
		
		// Response has headers, a body and a status code
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "1442");
		
		List<DuckDTO> data = duckService.readAllDucks();
		
		// ResponseEntity(Body, Headers, HttpStatus)
		return new ResponseEntity<List<DuckDTO>>(data, httpHeaders, HttpStatus.OK);
	}
}
```

### Request Mappings

Spring Web supplies us with many different annotations we can use on our controllers, the most commonly seen are:

- `@PatchMapping`
- `@GetMapping`
- `@DeleteMapping`
- `@PostMapping`

These mappings are applied to methods, and can be supplied an optional `path`:

```
// In DuckController with base path of localhost:8080/duck
// - The GetMapping defines a path of localhost:8080/duck/alt
// - We also have to supply a query parameter, appended to the url: localhost:8080/duck/alt?id=1
@GetMapping("/alt")
public ResponseEntity<DuckDTO> getDuckByIdAlt(@RequestParam("id") int id) {
  DuckDTO duck = duckService.readById(id);

  return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
}
```

These mappings correspond to a specific type of HTTP request.

### @RequestParam, @PathVariable, @PathParam

`@PathParam` is excluded from these examples, `@RequestParam` is a more powerful version that is implemented by Spring Boot.

The `@RequestParam` annotation allows us to get query parameters from requests url, a query parameter looks like `?id=1` and can be chained with amphersands `?id=1&name=fred`.

The `@PathVariable` annotation allows us to get variables from a url path. Specify a path variable with `/{somePathVariableName}` within your URLs path.

```
// Specify a query parameter within your methods signature, specifying the name of the key in the key=value pair.
// This will retrieve the value and put it into the corresponding method parameter as long as the data matches the type.
@GetMapping("/alt")
public ResponseEntity<DuckDTO> getDuckByIdAlt(@RequestParam("id") int id) {
  DuckDTO duck = duckService.readById(id);

  return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
}

// localhost:8080/duck/name/fred
@GetMapping("/name/{name}")
public ResponseEntity<DuckDTO> getDuckByName(@PathVariable("name") String name) {
  DuckDTO duck = duckService.readByName(name);

  return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
}
```

## What is a Service

A service is responsible for implementing business logic, we can indicate a service to be registered as a bean using the `@Service` annotation. This annotation is a specialisation of `@Component` which also registers a component as a Spring bean.

```
@Service // labelled as a bean (managed by Spring)
public class DuckService {
	
	// Data Access Object
	private DuckRepository duckRepository;
	
	private DuckMapper duckMapper;
	
	@Autowired
	public DuckService(DuckRepository duckRepository, DuckMapper duckMapper) {
		this.duckRepository = duckRepository;
		this.duckMapper = duckMapper;
	}

	public List<DuckDTO> readAllDucks() {
		List<Duck> ducks = duckRepository.findAll();
		List<DuckDTO> duckDTOs = new ArrayList<DuckDTO>();
		
		ducks.forEach(duck -> duckDTOs.add(duckMapper.mapToDTO(duck)));
		
		return duckDTOs;
	}
}
```

## What is a Repository

A repository in Spring Boot is a Data Access Object (DAO) that can be registered as a bean using the `@Repository` annotation. Repositories can be supplied with some basic CRUD functionality thanks to the Spring Data JPA, Hibernate and the JDBC, this saves a lot of time as we no longer have to focus on mapping objects to table data and vice-versa.

To create a standard, database-independent repository, we should extend from the `JpaRepository` interface within our repository interface. We use an interface as we don't provide the database connection implementation ourselves, Spring Data JPA and Hibernate provide the implementation.

```
// The type specified, Duck, is the type of data that this repository will be accessing
// - It is able to do this as the Duck model class is registered as an Entity
@Repository
public interface DuckRepository extends JpaRepository<Duck, Integer> {

}
```

## What is a Entity model

An Entity model in Spring is used by the JPA implementation (Hibernate) to create database tables and generate SQL to run against our database using the JDBC driver implementation. We can signal an entity with the `@Entity` annotation, and apply various annotations for validation (database constraints). The entity model is usually the business domain model:

```
@Entity // JPA + Hibernate will auto-create table for this class
public class Duck {

	// Validation Rules specify what can and can't be processed in our DB
	
	@Id // Auto-incrementing
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", unique = true) // Setting the name of a column and specifying that any data inserted into the table must be unique
	@NotNull
	private String name;
	
	@NotNull // The data cannot be null
	private String colour;
	
	@NotNull
	private String habitat;
	
	@Min(0) // The data must be 0 at a minimum (age >= 0)
	@Max(36) // The data can be 36 at most (age <= 36)
	private int age;
  
}
```

We can also have our validation rules applied before our service or repository are ever reached, which means hibernate never needs to run and waste processing power. We do this with the `@Valid` annotation applied to a controller methods parameter.

```
@PostMapping
public ResponseEntity<DuckDTO> createDuck(@Valid @RequestBody Duck duck) {
  DuckDTO newDuck = duckService.createDuck(duck);

  HttpHeaders headers = new HttpHeaders();
  headers.add("Location", String.valueOf(newDuck.getId()));

  return new ResponseEntity<DuckDTO>(newDuck, headers, HttpStatus.CREATED);
}
```

## General Data Flow of a Spring Rest API

![image](https://user-images.githubusercontent.com/67016030/110527894-6db4f380-810f-11eb-963a-fecdc4807747.png)

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
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A duck was not found, QUACK!!!")
public class DuckNotFoundException extends EntityNotFoundException {

	public DuckNotFoundException() {
		super(); // calls EntityNotFoundException()
	}

	public DuckNotFoundException(String message) {
		super(message); // calls EntityNotFoundException(String message)
	}
}
```

The above `DuckNotFoundException` is a custom exception inheriting from the `EntityNotFoundException`, we can throw this in our code like any other exception. We can also specify a simple response by annotating the exception with `@ResponseStatus`. The above example states:

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

## Unit Testing a Component

## Prepopulating a database for a Integration Test

## Integration Testing a Component



# Acknowledgment

[Jordan Harrison](https://github.com/JHarry444/SpringDucks) - Author of project used to guide the lessons
