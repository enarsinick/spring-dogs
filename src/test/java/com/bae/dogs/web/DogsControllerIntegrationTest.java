package com.bae.dogs.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.dogs.domain.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:dog-schema.sql", "classpath:dog-data.sql" }, 
				 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class DogsControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testCreate() throws Exception {
		Dog testDog = new Dog("Bindi", 5, "Wolf");
		String testDogAsJOSN = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJOSN);
		
		Dog testCreatedDog = new Dog(2,"Bindi", 5, "Wolf");
		String testCreatedDogAsJOSN = this.mapper.writeValueAsString(testCreatedDog);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJOSN);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	 
	@Test
	public void testGetAll() throws Exception {
		Dog dog = new Dog(1,"Fred", 1, "Border Collie");
		String dogsAsJSON = this.mapper.writeValueAsString(List.of(dog));
		
		RequestBuilder req = get("/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(dogsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testGetDog() throws Exception {
		Dog dog = new Dog(1,"Fred", 1, "Border Collie");
		String dogJSON = this.mapper.writeValueAsString(dog);
		RequestBuilder req = get("/get/" + dog.getId()).contentType(MediaType.APPLICATION_JSON).content(dogJSON);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(dogJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReplaceDog() throws Exception {
		Dog dog = new Dog(1, "Fred", 1, "Border Collie");
		String dogJSON = this.mapper.writeValueAsString(dog);
		RequestBuilder req = put("/replace/" + dog.getId()).contentType(MediaType.APPLICATION_JSON).content(dogJSON);
	
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(dogJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	public void testDelete() throws Exception {
		Dog dog = new Dog(1, "Fred", 1, "Border Collie");
		RequestBuilder req = delete("/delete/" + dog.getId()).contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	

}
