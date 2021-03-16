package com.qa.dto;

import java.util.List;

import com.qa.model.Todo;

public class UserDTO {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private List<TodoDTO> todos;
	
	public UserDTO() {
		super();
	}

	public UserDTO(int id, String firstName, String lastName, String userName, List<TodoDTO> todos) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.todos = todos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<TodoDTO> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoDTO> todos) {
		this.todos = todos;
	}

	
	
	
}
