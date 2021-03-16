package com.qa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "todo_id")
	private int id;

	@NotNull
	private String title;

	private String memo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateCreated;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateCompleted;

	private boolean important;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "fk_user_id")
	private User user;

	
}
