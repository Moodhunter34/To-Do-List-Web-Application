package com.qa.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.mappers.TodoMapper;
import com.qa.repository.TodoRepository;
import com.qa.model.Todo;
import com.qa.dto.TodoDTO;
import com.qa.exceptions.TodoNotFoundException;
import com.qa.exceptions.UserNotFoundException;

@Service
public class TodoService {

	private TodoRepository todoRepository;
	
	private TodoMapper todoMapper;
	
	@Autowired
	public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.todoMapper = todoMapper;
	}
	
	public List<TodoDTO> readAllTodos() {
		List<Todo> todosInDb = todoRepository.findAll();
		List<TodoDTO> returnables = new ArrayList<TodoDTO>();
		
		todosInDb.forEach(todo -> {
			returnables.add(todoMapper.mapToDTO(todo));
		});
		
		return returnables;
	}
	
	public TodoDTO createTodo(Todo todo) {
		Todo savedTodo = todoRepository.save(todo);
		
		return todoMapper.mapToDTO(savedTodo);
	}
	
	public Boolean deleteTodo(Integer id) {
		if (todoRepository.existsById(id)) {
			todoRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException();
		}
		
		boolean doesItStillExist = todoRepository.existsById(id);
		
		return !doesItStillExist;
	}
	
	public TodoDTO readTodoByTitle(String title) {
		Todo todo = todoRepository.getTodoByTitleJPQL(title);
		
		return todoMapper.mapToDTO(todo);
	}
	
	public TodoDTO updateTodo(Integer id, Todo todo) throws EntityNotFoundException {
		Optional<Todo> todoInDbOpt = todoRepository.findById(id);
		Todo todoInDb;
		
		if (todoInDbOpt.isPresent()) {
			todoInDb = todoInDbOpt.get();
		} else {
			throw new TodoNotFoundException("Todo not found");
		}
		
		todoInDb.setTitle(todo.getTitle());
		todoInDb.setMemo(todo.getMemo());
		todoInDb.setDateCreated(todo.getDateCreated());
		todoInDb.setDateCompleted(todo.getDateCompleted());
		todoInDb.setImportant(todo.isImportant());
		
		Todo updatedTodo = todoRepository.save(todoInDb);
		
		return todoMapper.mapToDTO(updatedTodo);
	}
}
