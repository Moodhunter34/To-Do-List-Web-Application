package com.qa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

	@Id // Auto-incrementing
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "firstName")
	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@Column(unique = true)
	@NotNull
	private String userName;

	@NotNull
	private String password;


	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Todo> todos;
	

	

}
