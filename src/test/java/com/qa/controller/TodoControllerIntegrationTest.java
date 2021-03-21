package com.qa.controller;

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

	private Todo validTodo = new Todo(1, "Walk the dog", "Walk the dog everyday day and night", false,
			user);
	private TodoDTO todoDTO = new TodoDTO(1, "Walk the dog", "Walk the dog everyday day and night", false);

	private List<Todo> validTodos = List.of(validTodo);
	private List<TodoDTO> validTodoDTOs = List.of(todoDTO);

	@Test
	public void createTodoTest() throws Exception {
		Todo todoToSave = new Todo(0, "Walk the dog", "Walk the dog everyday day and night", false, user);

		TodoDTO expectedTodo = new TodoDTO(0, "Walk the dog", "Walk the dog everyday day and night", false);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/todo");

		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(todoToSave));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedTodo));

		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");

		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);
	}

	@Test
	public void getAllTodosTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/todo");
		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validTodoDTOs));

		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void getTodoByTitleTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/todo/title/Walk the dog");
		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todoDTO));

		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void updateTodoTest() throws Exception {
		Todo updatedTodo = new Todo(1, "Walk the dog", "Walk the dog everyday day and night", false, user);

		TodoDTO expectedTodo = new TodoDTO(1, "Walk the dog", "Walk the dog everyday day and night", false);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/todo/1");

		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(updatedTodo)); // sending User in

		// Specify what data type we expect in response
		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedTodo)); // expecting UserDTO back

		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void deleteTodoTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/todo/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");

		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

}
