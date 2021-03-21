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
	
//	@Query("SELECT us FROM User us WHERE us.userName = ?1")
//	public User getUserByUserNameJPQL(String userName);
	
	// JPQL and Native Queries can be passed parameters in the same way
	// - Uses '?<num>' syntax to select the corresponding parameter from the method
	// parameters
//	@Query("SELECT u FROM User u WHERE u.firstName = ?1")
//	public User getUserByFirstNameJPQL(String firstName);

}
