package com.qa.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.UserDTO;
import com.qa.service.UserService;
import com.qa.model.User;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> data = userService.readAllUsers();

		return new ResponseEntity<List<UserDTO>>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {

		UserDTO newUser = userService.createUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newUser.getId()));

		return new ResponseEntity<UserDTO>(newUser, headers, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		UserDTO updatedUser = userService.updateUser(id, user);

		return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(userService.deleteUser(id), HttpStatus.OK);
	}

}
