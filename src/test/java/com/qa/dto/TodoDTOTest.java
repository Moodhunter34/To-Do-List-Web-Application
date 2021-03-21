package com.qa.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TodoDTOTest {

	private static TodoDTO testTodoDTO;
	
	private TodoDTO to;
	
	@BeforeEach
	public void init() {
		to = new TodoDTO(1, "Go hiking", "Go hiking on mount Olympus", true);
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TodoDTO.class).verify();
	}

	@Test
	public void noArgsConstructorTest() {
		testTodoDTO = new TodoDTO();
	}

	@Test
	public void allArgsConstructorTest() {
		assertNotNull(testTodoDTO);
		assertTrue(testTodoDTO instanceof TodoDTO);
	}

	@Test
	public void getIdTest() {
		assertEquals(1, to.getId());
	}

	@Test
	public void setIdTest() {
		to.setId(1);
		assertEquals(1, to.getId());
	}
	
	@Test
	public void getTitleTest() {
		assertEquals("Go hiking", to.getTitle());
	}
	
	@Test
	public void setTitleTest() {
		to.setTitle("Go hiking");
		assertEquals("Go hiking", to.getTitle());
	}
	
	@Test
	public void getMemoTest() {
		assertEquals("Go hiking on mount Olympus", to.getMemo());
	}
	
	@Test
	public void setMemoTest() {
		to.setMemo("Go hiking on mount Olympus");
		assertEquals("Go hiking on mount Olympus", to.getMemo());
	}
	
	@Test
	public void isImportantTest() {
		assertEquals(true, to.isImportant());
	}
	
	@Test
	public void setImportantTest() {
		to.setImportant(true);
		assertEquals(true, to.isImportant());
	}

}
