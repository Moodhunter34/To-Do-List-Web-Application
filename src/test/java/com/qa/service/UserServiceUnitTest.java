package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.dto.UserDTO;
import com.qa.mappers.UserMapper;
import com.qa.model.User;
import com.qa.repository.UserRepository;

@SpringBootTest
public class UserServiceUnitTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserMapper userMapper;

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
	public void readAllUsersTest() {
		when(userRepository.findAll()).thenReturn(users);
		when(userMapper.mapToDTO(validUser)).thenReturn(validUserDTO);

		assertThat(userDTOs).isEqualTo(userService.readAllUsers());

		verify(userRepository, times(1)).findAll();
		verify(userMapper, times(1)).mapToDTO(validUser);
	}
	
	@Test
	public void readByIdTest() {
		
	}
	
	@Test
	public void createUserTest() {
		when(userRepository.save(Mockito.any(User.class))).thenReturn(validUser);
		when(userMapper.mapToDTO(Mockito.any(User.class))).thenReturn(validUserDTO);
		
		assertThat(validUserDTO).isEqualTo(userService.createUser(validUser));
		
		verify(userRepository, times(1)).save(Mockito.any(User.class));
		verify(userMapper, times(1)).mapToDTO(Mockito.any(User.class));
	}

	@Test
	public void updateUserTest() {
		User updatedUser = new User(1, "Nikos", "Pap", "nikpap", "nik123",  null);
		UserDTO updatedUserDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);

		when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validUser));

		when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

		when(userMapper.mapToDTO(Mockito.any(User.class))).thenReturn(updatedUserDTO);

		UserDTO toTestDTO = userService.updateUser(validUser.getId(), updatedUser);

		assertThat(updatedUserDTO).isEqualTo(toTestDTO);
	}
	
	@Test
	public void deleteUserTest() {
		when(userRepository.existsById(Mockito.any(Integer.class)))
			.thenReturn(true)
			.thenReturn(false);
		
		assertThat(true).isEqualTo(userService.deleteUser(1));
		
		verify(userRepository, times(2)).existsById(Mockito.any(Integer.class));
		verify(userRepository, times(1)).deleteById(Mockito.any(Integer.class));
	}
	

}
