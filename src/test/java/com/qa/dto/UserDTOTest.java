package com.qa.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;


public class UserDTOTest {
	
	private static UserDTO testUserDTO;
	
	private UserDTO us;
	
	@BeforeEach
	public void init() {
		 us = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(UserDTO.class).verify();
	}
	
	
	@Test
	public void noArgsConstructorTest() {
		testUserDTO = new UserDTO();
	}
	
	@Test
	public void allArgsConstructorTest() {
		testUserDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);

		assertNotNull(testUserDTO);
		assertTrue(testUserDTO instanceof UserDTO);
	}
	
	@Test
	public void getIdTest() {
		//UserDTO us = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
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
	public void getTodosTest() {
		assertEquals(null, us.getTodos());
	}
	
	@Test
	public void setTodosTest() {
		us.setTodos(null);
		assertEquals(null, us.getTodos());
	}
	

}
