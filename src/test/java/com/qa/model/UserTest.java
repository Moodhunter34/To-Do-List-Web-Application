package com.qa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

	private static User testUser;

	private User us;

	@BeforeEach
	public void init() {
		us = new User(1, "Nikos", "Pap", "nikpap", "nik123");
	}

	@Test
	public void noArgsConstructorTest() {
		testUser = new User();
	}

	@Test
	public void allArgsConstructorTest() {
		us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
	}

	@Test
	public void altArgsConstructorTest() {
		assertNotNull(testUser);
		assertTrue(testUser instanceof User);
	}

	@Test
	public void getIdTest() {
		assertEquals(1, us.getId());
	}

	@Test
	public void setIdTest() {
		us.setId(1);
		assertEquals(1, us.getId());
	}

	@Test
	public void getFirstNameTest() {
		assertEquals("Nikos", us.getFirstName());
	}

	@Test
	public void setFirstNameTest() {
		us.setFirstName("Nikos");
		assertEquals("Nikos", us.getFirstName());
	}

	@Test
	public void getLastNameTest() {
		assertEquals("Pap", us.getLastName());
	}

	@Test
	public void setLastNameTest() {
		us.setLastName("Pap");
		assertEquals("Pap", us.getLastName());
	}

	@Test
	public void getUserNameTest() {
		assertEquals("nikpap", us.getUserName());
	}

	@Test
	public void setUserNameTest() {
		us.setUserName("nikpap");
		assertEquals("nikpap", us.getUserName());
	}

	@Test
	public void getPasswordTest() {
		assertEquals("nik123", us.getPassword());
	}

	@Test
	public void setPasswordTest() {
		us.setPassword("nik123");
		assertEquals("nik123", us.getPassword());
	}

	@Test
	public void getTodosTest() {
		assertEquals(null, us.getTodos());
	}

	@Test
	public void setTodosTest() {
		us.setTodos(null);
		assertEquals(null, us.getTodos());
	}

}
