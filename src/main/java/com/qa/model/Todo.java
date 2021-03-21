package com.qa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	private boolean important;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User user;

	public Todo() {

	}

	public Todo(int id, @NotNull String title, String memo, boolean important, User user) {
		super();
		this.id = id;
		this.title = title;
		this.memo = memo;
		this.important = important;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
