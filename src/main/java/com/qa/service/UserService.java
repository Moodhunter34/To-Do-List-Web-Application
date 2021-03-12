package com.qa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto.UserDTO;
import com.qa.exceptions.UserNotFoundException;
import com.qa.mappers.UserMapper;
import com.qa.model.User;
import com.qa.repository.UserRepository;


@Service
public class UserService {
	
	// Data access object
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
		
		users.forEach(user -> userDTOs.add(userMapper.mapToDTO(user)));
		
		return userDTOs;
	}
	
	public UserDTO readById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return userMapper.mapToDTO(user.get());
		} else {
			throw new UserNotFoundException("User not found");
		}
	}
	
	public UserDTO readByFirstName(String firstName) {
		User user =  userRepository.getUserByFirstNameSQL(firstName);
		
		return userMapper.mapToDTO(user);
	}
	
	public UserDTO createUser(User user) {
		User newUser = userRepository.save(user);
		
		return userMapper.mapToDTO(newUser);
	}
	
	public UserDTO updateUser(Integer id, User user) throws EntityNotFoundException {
		Optional<User> userInDbOpt = userRepository.findById(id);
		User userInDb;
		
		if (userInDbOpt.isPresent()) {
			userInDb = userInDbOpt.get();
		} else {
			throw new UserNotFoundException("User not found");
		}
		
		userInDb.setFirstName(user.getFirstName());
		userInDb.setLastName(user.getLastName());
		userInDb.setUserName(user.getUserName());
		userInDb.setPassword(user.getPassword());
		
		User updatedUser = userRepository.save(userInDb);
		
		return userMapper.mapToDTO(updatedUser);
	}
	
	public boolean deleteUser(Integer id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException();
		}
		userRepository.deleteById(id);
		
		boolean exists = userRepository.existsById(id);
		
		return !exists;
	}

}
