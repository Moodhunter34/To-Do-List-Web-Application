package com.qa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "user")
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

//	@Column(unique = true)
//	@NotNull
	private String userName;

//	@NotNull
	private String password;


	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Todo> todos;
	
	public User() {
		
	}

	public User(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String userName,
			@NotNull String password, List<Todo> todos) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.todos = todos;
	}
	
	public User(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String userName,
			@NotNull String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + id;
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + ((todos == null) ? 0 : todos.hashCode());
//		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (id != other.id)
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (password == null) {
//			if (other.password != null)
//				return false;
//		} else if (!password.equals(other.password))
//			return false;
//		if (todos == null) {
//			if (other.todos != null)
//				return false;
//		} else if (!todos.equals(other.todos))
//			return false;
//		if (userName == null) {
//			if (other.userName != null)
//				return false;
//		} else if (!userName.equals(other.userName))
//			return false;
//		return true;
//	}
	
	

}
