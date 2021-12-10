package com.bae.dogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.dogs.domain.Dog;
import com.bae.dogs.repo.DogsRepo;

@Service
public class DogsServiceDB implements DogService {
	
	private DogsRepo repo;
	
	@Autowired
	public DogsServiceDB(DogsRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Dog createDog(Dog dog) {
		Dog created = this.repo.save(dog);
		return created;
	}

	@Override
	public List<Dog> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Dog getDog(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Dog replaceDog(int id, Dog dog) {
		Dog existing = this.repo.findById(id).get();
		existing.setAge(dog.getAge());
		existing.setBreed(dog.getBreed());
		existing.setName(dog.getName());
		return this.repo.save(existing);
	}

	@Override
	public void deleteDog(int id) {
		this.repo.deleteById(id);
	}
	
}
