package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.dto.TodoDTO;
import com.qa.dto.UserDTO;
import com.qa.mappers.UserMapper;
import com.qa.model.Todo;
import com.qa.model.User;
import com.qa.repository.TodoRepository;
import com.qa.repository.UserRepository;

@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserMapper userMapper;

	private List<User> users;
	private List<UserDTO> userDTOs;

	private User user;
	private User validUser;
	private UserDTO validUserDTO;

	private Todo validTodo;
	private TodoDTO validTodoDTO;

	@BeforeEach
	public void init() {
//		validUser = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
//		validUserDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
//
//		users = new ArrayList<User>();
//		userDTOs = new ArrayList<UserDTO>();
//
//		userRepository.deleteAll();
//
//		validUser = userRepository.save(validUser);
//
//		validUserDTO = userMapper.mapToDTO(validUser);
//
//		users.add(validUser);
//		userDTOs.add(validUserDTO);

		validUser = new User(1, "Nikos", "Pap", "nikpap", "nikpap8");
		
		validTodo = new Todo(1,"Go running", "Go for a run outside", true, user);
		validTodo.setUser(new User(1, "Nikos", "Pap", "nikpap", "nikpap8"));
		
		userRepository.deleteAll();
		userRepository.flush();
		
		validUser = userRepository.saveAndFlush(validUser);
		validTodo.setUser(validUser);
		validTodo = todoRepository.saveAndFlush(validTodo);
		
		validTodoDTO = new TodoDTO(validTodo.getId(), "Go running", "Go for a run outside", true);
		validUserDTO = new UserDTO(validUser.getId(), "Nikos", "Pap", "nikpap", List.of(validTodoDTO));
		
		users = List.of(validUser);
		userDTOs = List.of(validUserDTO);

	}

	@Test
	public void readAllUsersTest() {
		List<UserDTO> usersInDb = userService.readAllUsers();

		assertThat(userDTOs).isEqualTo(usersInDb);
	}

	@Test
	public void createUserTest() {
		User newUser = new User(2, "Alex", "Gian", "alexg", "yeah23");
		UserDTO expectedUserDTO = userMapper.mapToDTO(newUser);

		UserDTO savedUser = userService.createUser(newUser);
		expectedUserDTO.setId(savedUser.getId());

		assertThat(savedUser).isEqualTo(expectedUserDTO);
	}

	@Test
	public void deleteUserTest() {
		assertThat(userService.deleteUser(1)).isEqualTo(true);
	}
	
	@Test
	public void readByIdTest() {
	//	assertThat(userService.readById(1)).isEqualTo(1);
		
		UserDTO usersInDb = userService.readById(user.getId());

		assertThat(userDTOs).isEqualTo(usersInDb);
	}
	
	@Test
	public void readByFirstNameTest() {
		assertThat(userService.readByFirstName("Nikos")).isEqualTo("Nikos");
	}
	
	@Test
	public void readByUserNameTest() {
		assertThat(userService.readByUserName("nikpap")).isEqualTo("nikpap");
	}
}
