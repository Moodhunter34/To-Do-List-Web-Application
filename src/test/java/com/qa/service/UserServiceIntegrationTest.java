package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.dto.UserDTO;
import com.qa.mappers.UserMapper;
import com.qa.model.User;
import com.qa.repository.UserRepository;


@SpringBootTest
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	private List<User> users;
	private List<UserDTO> userDTOs;
	
	private User validUser;
	private UserDTO validUserDTO;
	
	
	
	@BeforeEach
	public void init() {
		validUser = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
	//	validUserDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
		
		users = new ArrayList<User>();
		userDTOs = new ArrayList<UserDTO>();
		
		userRepository.deleteAll();
		
		validUser = userRepository.save(validUser);
		
		validUserDTO = userMapper.mapToDTO(validUser);
		
		users.add(validUser);
		userDTOs.add(validUserDTO);
	}
	
	@Test
	public void readAllUsersTest() {
		List<UserDTO> usersInDb = userService.readAllUsers();
		
		assertThat(userDTOs).isEqualTo(usersInDb);
	}
}
