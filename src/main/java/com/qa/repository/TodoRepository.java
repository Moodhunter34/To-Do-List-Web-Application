package com.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

	public Todo findByTitle(String title);
	
//	@Query("SELECT t FROM Todo t WHERE t.title = ?1")
//	public Todo getTodoByTitleJPQL(String title);
//	
}
