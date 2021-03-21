package com.qa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {

	private static Todo testTodo;
	
	private User user;
	
	private Todo to;
	
	@BeforeEach
	public void init() {
		to = new Todo(1, "Walk the dog", "Walk the dog everyday day and night", true, user);
	}

	@Test
	public void noArgsConstructorTest() {
		testTodo = new Todo();
		assertNotNull(testTodo);
	}

	// Create an object using that constructor, assert that the object is equal to
	// an expected result
	@Test
	public void allArgsConstructorTest() {
		user = new User();

		assertNotNull(testTodo);
		assertTrue(testTodo instanceof Todo);
		// date = new Date();
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
		assertEquals("Walk the dog", to.getTitle());
	}

	@Test
	public void setTitleTest() {
		to.setTitle("Walk the dog");
		assertEquals("Walk the dog", to.getTitle());
	}

	@Test
	public void getMemoTest() {
		assertEquals("Walk the dog everyday day and night", to.getMemo());
	}

	@Test
	public void setMemoTest() {
		to.setMemo("Walk the dog everyday day and night");
		assertEquals("Walk the dog everyday day and night", to.getMemo());
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

	@Test
	public void getUserTest() {
		assertEquals(user, to.getUser());
	}

	@Test
	public void setUserTest() {
		to.setUser(user);
		assertEquals(user, to.getUser());
	}

}
