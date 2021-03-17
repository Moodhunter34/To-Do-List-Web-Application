package com.qa.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class TodoTest {
	
	private static Todo testTodo;
	private User user;
	
	
	
	@Test
	public void noArgsConstructorTest() {
		testTodo = new Todo();
		assertNotNull(testTodo);
	}
	
	// Create an object using that constructor, assert that the object is equal to an expected result
	@Test
	public void allArgsConstructorTest() {
		user = new User();
		testTodo = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		
		assertNotNull(testTodo);
		assertTrue(testTodo instanceof Todo);
	//	date = new Date();
	}
	
	@Test
	public void getIdTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		assertEquals(1, to.getId());
	}
	
	@Test
	public void setIdTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		to.setId(1);
		assertEquals(1, to.getId());
	}
	
	@Test
	public void getTitleTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		assertEquals("Walk the dog", to.getTitle());
	}
	
	@Test
	public void setTitleTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		to.setTitle("Walk the dog");
		assertEquals("Walk the dog", to.getTitle());
	}
	
	@Test
	public void getMemoTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		assertEquals("Walk the dog everyday day and night", to.getMemo());
	}
	
	@Test
	public void setMemoTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		to.setMemo("Walk the dog everyday day and night");
		assertEquals("Walk the dog everyday day and night", to.getMemo());
	}
	
	@Test
	public void getDateCreatedTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		assertEquals(Date.valueOf("2020-10-15"), to.getDateCreated());
	}
	
	@Test
	public void setDateCreatedTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		to.setDateCreated(Date.valueOf("2020-10-15"));
		assertEquals(Date.valueOf("2020-10-15"), to.getDateCreated());
	}
	
	@Test
	public void getDateCompletedTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		assertEquals(Date.valueOf("2021-03-16"), to.getDateCompleted());
	}
	
	@Test
	public void setDateCompletedTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		to.setDateCompleted(Date.valueOf("2021-03-16"));
		assertEquals(Date.valueOf("2021-03-16"), to.getDateCompleted());
	}
	
	@Test
	public void isImportantTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), true, user);
		assertEquals(true, to.isImportant());
	}
	
	@Test
	public void setImportantTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), true, user);
		to.setImportant(true);
		assertEquals(true, to.isImportant());
	}
	
	@Test
	public void getUserTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), true, user);
		assertEquals(user, to.getUser());
	}
	
	@Test
	public void setUserTest() {
		Todo to = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), true, user);
		to.setUser(user);
		assertEquals(user, to.getUser());
	}

}
