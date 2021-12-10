package com.bae.dogs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.dogs.domain.Dog;
import com.bae.dogs.service.DogService;

@RestController
public class DogsController {
	
	private DogService service;
	
	@Autowired
	public DogsController(DogService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") // 201
	public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
		Dog created = this.service.createDog(dog);
		return new ResponseEntity<Dog>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll") // 200
	public List<Dog> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping("/get/{id}") // 200
	public Dog getDog(@PathVariable int id) {
		return this.service.getDog(id);
	}
	
	@PutMapping("/replace/{id}") // 202
	public ResponseEntity<Dog> replaceDog(@PathVariable int id, @RequestBody Dog dog) {
		this.service.replaceDog(id, dog);
		return new ResponseEntity<Dog>(dog, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}") // 204
	public ResponseEntity<Dog> deleteDog(@PathVariable int id) {
		this.service.deleteDog(id);
		return new ResponseEntity<Dog>(HttpStatus.NO_CONTENT);
	}
}
