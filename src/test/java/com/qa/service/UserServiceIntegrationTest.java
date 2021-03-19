package com.qa.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.dto.UserDTO;
import com.qa.mappers.UserMapper;
import com.qa.model.Todo;
import com.qa.model.User;
import com.qa.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


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
	
	
	
//	@BeforeEach
//	public void init() {
//		validUser = new User("Nikos", "Pap", "nikpap", "nik123", todos);
//	}
}
