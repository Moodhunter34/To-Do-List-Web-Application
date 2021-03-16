package com.qa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.dto.UserDTO;
import com.qa.model.User;
import com.qa.service.UserService;

@SpringBootTest
public class UserControllerUnitTest {
	
	@Autowired
	private UserController userController;
	
	@MockBean
	private UserService userService;
	
	private List<User> users;
	private List<UserDTO> userDTOs;
	
	private User validUser;
	private UserDTO validUserDTO;
	
	@BeforeEach
	public void init() {
		validUser = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		validUserDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
		
		users = new ArrayList<User>();
		userDTOs = new ArrayList<UserDTO>();
		
		users.add(validUser);
		userDTOs.add(validUserDTO);
	}
	
	@Test
	public void getAllUsersTest() {
		when(userService.readAllUsers()).thenReturn(userDTOs);
		
		ResponseEntity<List<UserDTO>> response = 
				new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(userController.getAllUsers());
		
		verify(userService, times(1)).readAllUsers();
	}
	
	@Test
	public void getUserByIdTest() {
		when(userService.readById(Mockito.any(Integer.class))).thenReturn(validUserDTO);
		
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(validUserDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(userController.getUserById(validUser.getId()));
		
		verify(userService, times(1)).readById(Mockito.any(Integer.class));
	}
	
	@Test
	public void getUserByIdAltTest() {
		when(userService.readById(Mockito.any(Integer.class))).thenReturn(validUserDTO);
		
		ResponseEntity<UserDTO> response =
				new ResponseEntity<UserDTO>(validUserDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(userController.getUserByIdAlt(validUser.getId()));
		
		verify(userService, times(1)).readById(Mockito.any(Integer.class));
	}
	
	@Test
	public void createUserTest() {
		when(userService.createUser(Mockito.any(User.class))).thenReturn(validUserDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validUserDTO.getId()));
		
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(validUserDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(userController.createUser(validUser));
		
		verify(userService, times(1)).createUser(Mockito.any(User.class));
		
	}
	
	@Test
	public void deleteUserTest() {
		when(userService.deleteUser(Mockito.any(Integer.class))).thenReturn(true);
		
		ResponseEntity<Boolean> response = 
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(response).isEqualTo(userController.deleteUser(validUser.getId()));
		
		verify(userService, times(1)).deleteUser(Mockito.any(Integer.class));
	}
	
	@Test
	public void updateUserTest() {
		// mocking the update user method
		when(userService.updateUser(Mockito.any(Integer.class), Mockito.any(User.class))).thenReturn(validUserDTO);
		
		//expected response
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(validUserDTO, HttpStatus.OK);
		
		//check the response
		assertThat(response).isEqualTo(userController.updateUser(validUser.getId(), validUser));
		
		
		//verify the response
		verify(userService, times(1)).updateUser(Mockito.any(Integer.class), Mockito.any(User.class));
		
		
	}
	
}
