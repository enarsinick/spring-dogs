package com.bae.dogs.service;

import java.util.List;

import com.bae.dogs.domain.Dog;

public interface DogService {
	
	public Dog createDog(Dog dog);
	
	public List<Dog> getAll();
	
	public Dog getDog(int id);
	
	public Dog replaceDog(int id, Dog dog);
	
	public void deleteDog(int id);

}
