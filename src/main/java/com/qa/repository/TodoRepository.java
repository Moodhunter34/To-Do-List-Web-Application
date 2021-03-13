package com.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

	
	
}
