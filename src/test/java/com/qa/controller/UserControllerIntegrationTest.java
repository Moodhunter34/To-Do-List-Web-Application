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
import com.qa.dto.UserDTO;
import com.qa.mappers.UserMapper;
import com.qa.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private User validUser = new User(1, "Nikos", "Pap", "nikpap", "nik123", null);
	private UserDTO userDTO = new UserDTO(1, "Nikos", "Pap", "nikpap", null);
	
	private List<User> validUsers = List.of(validUser);
	private List<UserDTO> validUserDTOs = List.of(userDTO);
	
	
	@Test
	public void createUserTest() throws Exception {
		User userToSave = new User(2, "Vaggelos", "Miskos", "vag8", "vag890", null);
		UserDTO expectedUser = new UserDTO(2, "Vaggelos", "Miskos", "vag8", null);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/user");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON); // Mime-Type
		mockRequest.content(objectMapper.writeValueAsString(userToSave));
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedUser)); 
		
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");

		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/user");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validUserDTOs));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void getUserByIdTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/user/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(userDTO));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void getUserByIdAltTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/user/alt");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		mockRequest.queryParam("id", "1");
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(userDTO));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void updateUserTest() throws Exception {
		User updatedUser = new User(2, "Vaggelos", "Miskos", "vag8", "vag890", null);
		UserDTO expectedUser = new UserDTO(2, "Vaggelos", "Miskos", "vag8", null);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/duck/1");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON); 
		mockRequest.content(objectMapper.writeValueAsString(updatedUser)); // sending User in
		
		// Specify what data type we expect in response
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedUser)); // expecting UserDTO back
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/user/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
}
