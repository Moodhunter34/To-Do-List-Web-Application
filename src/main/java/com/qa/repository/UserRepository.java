package com.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// DERIVED QUERY
	public User findByFirstName(String name);
	
	public User findByUserName(String userName);
	
	// NATIVE QUERY
//	@Query(value = "SELECT * FROM user", nativeQuery = true)
//	public List<User> getAllUsersSQL();
//	
//	
//	
//	@Query("SELECT u FROM User u")
//	public List<User> getAllUsersJPQL();
//	
	
	@Query(value = "SELECT * FROM User WHERE firstName = ?1", nativeQuery = true)
	public User getUserByFirstNameSQL(String firstName);
	
}
