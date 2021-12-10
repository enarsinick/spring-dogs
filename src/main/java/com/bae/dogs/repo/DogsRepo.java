package com.bae.dogs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.dogs.domain.Dog;

@Repository
public interface DogsRepo extends JpaRepository<Dog, Integer>{
}
