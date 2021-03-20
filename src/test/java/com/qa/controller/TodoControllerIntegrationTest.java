package com.qa.controller;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dto.TodoDTO;
import com.qa.mappers.TodoMapper;
import com.qa.model.Todo;
import com.qa.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private ObjectMapper objectMapper;

	private User user;

	private Todo validTodo = new Todo(1, "Walk the dog", "Walk the dog everyday day and night",
			Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false, user);
	private TodoDTO todoDTO = new TodoDTO(1, "Walk the dog", "Walk the dog everyday day and night",
			Date.valueOf("2020-10-15"), Date.valueOf("2021-03-16"), false);

	private List<Todo> validTodos = List.of(validTodo);
	private List<TodoDTO> validTodoDTOs = List.of(todoDTO);
	
	@Test
	public void createTodoTest() throws Exception {
		Todo todoToSave = new Todo(1,"Go for a run", "Go for a run at the park",
			Date.valueOf("2020-11-15"), Date.valueOf("2021-03-18"), false, user);
		
		TodoDTO expectedTodo = new TodoDTO(1,"Go for a run", "Go for a run at the park",
			Date.valueOf("2020-11-15"), Date.valueOf("2021-03-18"), false);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/todo");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(todoToSave));
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedTodo));
		
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
	
	
}
