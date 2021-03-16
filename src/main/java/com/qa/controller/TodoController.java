package com.qa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.TodoDTO;
import com.qa.model.Todo;
import com.qa.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	private TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDTO>> getAllTodos() {
		List<TodoDTO> data = todoService.readAllTodos();
		
		return new ResponseEntity<List<TodoDTO>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<TodoDTO> getTodoByTitle(@PathVariable("title") String title) {
		TodoDTO todo = todoService.readTodoByTitle(title);
		
		return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody Todo todo) {
		TodoDTO newTodo = todoService.createTodo(todo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newTodo.getId()));
		
		return new ResponseEntity<TodoDTO>(newTodo, headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTodo(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(todoService.deleteTodo(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") int id,
										@RequestBody Todo todo) {
		TodoDTO updatedTodo = todoService.updateTodo(id, todo);
		
		return new ResponseEntity<TodoDTO>(updatedTodo, HttpStatus.OK);
	}
	
	

}
