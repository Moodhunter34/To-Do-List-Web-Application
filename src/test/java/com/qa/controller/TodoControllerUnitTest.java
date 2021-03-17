package com.qa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.dto.TodoDTO;
import com.qa.model.Todo;
import com.qa.model.User;
import com.qa.service.TodoService;

@SpringBootTest
public class TodoControllerUnitTest {
	
	@Autowired
	private TodoController todoController;
	
	@MockBean
	private TodoService todoService;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo validTodo;
	private TodoDTO validTodoDTO;
	
	private User user;
	
	@BeforeEach
	public void init() {
		user = new User();
		validTodo = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		validTodoDTO = new TodoDTO(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false);
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todos.add(validTodo);
		todoDTOs.add(validTodoDTO);
	}
	
	@Test
	public void getAllTodosTest() {
		when(todoService.readAllTodos()).thenReturn(todoDTOs);
		
		ResponseEntity<List<TodoDTO>> response = 
				new ResponseEntity<List<TodoDTO>>(todoDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(todoController.getAllTodos());
		
		verify(todoService, times(1)).readAllTodos();
	}
	
	@Test
	public void getTodoByTitleTest() {
		when(todoService.readTodoByTitle(Mockito.any(String.class))).thenReturn(validTodoDTO);
		
		ResponseEntity<TodoDTO> response =
				new ResponseEntity<TodoDTO>(validTodoDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(todoController.getTodoByTitle(validTodo.getTitle()));
		
		verify(todoService, times(1)).readTodoByTitle(Mockito.any(String.class));
	}
}
