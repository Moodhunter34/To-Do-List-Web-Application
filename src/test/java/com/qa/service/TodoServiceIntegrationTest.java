package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.dto.TodoDTO;
import com.qa.dto.UserDTO;
import com.qa.mappers.TodoMapper;
import com.qa.model.Todo;
import com.qa.model.User;
import com.qa.repository.TodoRepository;
import com.qa.repository.UserRepository;


@SpringBootTest
public class TodoServiceIntegrationTest {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoMapper todoMapper;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo validTodo;
	private TodoDTO validTodoDTO;
	
	private User validuser;
	
	@BeforeEach
	public void init() {
		validTodo = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", false, validuser);
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todoRepository.deleteAll();
		todoRepository.flush();
		validTodo = todoRepository.saveAndFlush(validTodo);
		
	//	validTodo = todoRepository.save(validTodo);
		validTodo.setUser(validuser);
		validTodo = todoRepository.saveAndFlush(validTodo);
		
		validTodoDTO = todoMapper.mapToDTO(validTodo);
		
		todos.add(validTodo);
		todoDTOs.add(validTodoDTO);
	}
	
	@Test
	public void readAllTodosTest() {
		List<TodoDTO> todosInDb = todoService.readAllTodos();
		
		assertThat(todoDTOs).isEqualTo(todosInDb);
	}
	
	@Test
	public void createTodoTest() {
		Todo newTodo = new Todo(1,"Walk the dog", "Walk the dog everyday day and night", false, validuser);
		TodoDTO expectedTodoDTO = todoMapper.mapToDTO(newTodo);

		TodoDTO savedTodo = todoService.createTodo(newTodo);
		expectedTodoDTO.setId(savedTodo.getId());

		assertThat(savedTodo).isEqualTo(expectedTodoDTO);
	}
	
	@Test
	public void deleteTodoTest() {
		assertThat(true).isEqualTo(todoService.deleteTodo(validTodo.getId()));
	}
	
	@Test
	public void updateTodoTest() {
		Todo newTodo = new Todo(validTodo.getId(), "Walk the dog", "Walk the dog everyday day and night", false, validuser);
		TodoDTO newTodoDTO = todoMapper.mapToDTO(newTodo);
		TodoDTO valTodoDTO = todoService.updateTodo(validTodo.getId(), newTodo);
		assertThat(newTodoDTO).isEqualTo(valTodoDTO);
	}
	
}
