package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.dto.TodoDTO;
import com.qa.mappers.TodoMapper;
import com.qa.model.Todo;
import com.qa.model.User;
import com.qa.repository.TodoRepository;

@SpringBootTest
public class TodoServiceUnitTest {
	
	@Autowired
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;
	
	@MockBean
	private TodoMapper todoMapper;
	
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
		todoDTOs= new ArrayList<TodoDTO>();
		
		todos.add(validTodo);
		todoDTOs.add(validTodoDTO);
	}
	
	@Test
	public void readAllTodosTest() {
		when(todoRepository.findAll()).thenReturn(todos);
		when(todoMapper.mapToDTO(validTodo)).thenReturn(validTodoDTO);
		
		assertThat(todoDTOs).isEqualTo(todoService.readAllTodos());
		
		verify(todoRepository, times(1)).findAll();
		verify(todoMapper, times(1)).mapToDTO(validTodo);
	}
	
	@Test
	public void createTodoTest() {
		when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(validTodo);
		when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);
		
		assertThat(validTodoDTO).isEqualTo(todoService.createTodo(validTodo));
		
		verify(todoRepository, times(1)).save(Mockito.any(Todo.class));
		verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
	}
	
	@Test
	public void deleteTodoTest() {
		when(todoRepository.existsById(Mockito.any(Integer.class)))
			.thenReturn(true)
			.thenReturn(false);
		
		assertThat(true).isEqualTo(todoService.deleteTodo(1));
		
		verify(todoRepository, times(2)).existsById(Mockito.any(Integer.class));
		verify(todoRepository, times(1)).deleteById(Mockito.any(Integer.class));
	}
	
	@Test
	public void readTodoByTitleTest() {
	}
	
	@Test
	public void updateTodoTest() {
		Todo updatedTodo = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
		TodoDTO updatedTodoDTO = new TodoDTO(1,"Walk the dog", "Walk the dog everyday day and night", Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false);
		
		when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTodo));
		
		when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(updatedTodo);
		
		when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(updatedTodoDTO);
		
		TodoDTO toTestDTO = todoService.updateTodo(validTodo.getId(), updatedTodo);
		
		assertThat(updatedTodoDTO).isEqualTo(toTestDTO);
	}
	

}
