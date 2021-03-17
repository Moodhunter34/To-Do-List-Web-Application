package com.qa.model;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class UserTest {
	
	private static User testUser;
	
	@Test
	public void noArgsConstructorTest() {
		testUser = new User();
	}
	
	@Test
	public void allArgsConstructorTest() {
		testUser = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		
		assertNotNull(testUser);
		assertTrue(testUser instanceof User);
	}
	
	@Test
	public void getIdTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals(1, us.getId());	
	}
	
	@Test
	public void setIdTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setId(1);
		assertEquals(1, us.getId());
	}
	
	@Test
	public void getFirstNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals("Nikos", us.getFirstName());
	}
	
	@Test
	public void setFirstNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setFirstName("Nikos");
		assertEquals("Nikos", us.getFirstName());
	}
	
	@Test
	public void getLastNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals("Pap", us.getLastName());
	}
	
	@Test
	public void setLastNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setLastName("Pap");
		assertEquals("Pap", us.getLastName());
	}
	
	@Test
	public void getUserNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals("nikpap", us.getUserName());
	}
	
	@Test
	public void setUserNameTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setUserName("nikpap");
		assertEquals("nikpap", us.getUserName());
	}
	
	@Test
	public void getPasswordTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals("nik123", us.getPassword());
	}
	
	@Test
	public void setPasswordTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setPassword("nik123");
		assertEquals("nik123", us.getPassword());
	}
	
	@Test
	public void getTodosTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		assertEquals(null, us.getTodos());
	}
	
	@Test
	public void setTodosTest() {
		User us = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
		us.setTodos(null);
		assertEquals(null, us.getTodos());
	}
	
	
	

}
