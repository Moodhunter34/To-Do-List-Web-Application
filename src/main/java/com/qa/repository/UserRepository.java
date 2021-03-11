package com.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	public User findByName(String name);
	
	@Query(value = "SELECT * FROM user", nativeQuery = true)
	public List<User> getAllUsersSQL();
	
	@Query("SELECT u FROM User u")
	public List<User> getAllUsersJPQL();
	
	@Query("SELECT u FROM User WHERE u.name = ?1")
	public User getUserByNameJPQL(String firstName);
}
