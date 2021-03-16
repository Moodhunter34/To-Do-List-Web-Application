package com.qa.dto;

import java.util.Date;
import java.util.List;

public class TodoDTO {

	private int id;

	private String title;

	private String memo;

	private Date dateCreated;

	private Date dateCompleted;

	private boolean important;

	private List<UserDTO> user;

	public TodoDTO() {
		super();
	}

	public TodoDTO(int id, String title, String memo, Date dateCreated, Date dateCompleted, boolean important,
			List<UserDTO> user) {
		super();
		this.id = id;
		this.title = title;
		this.memo = memo;
		this.dateCreated = dateCreated;
		this.dateCompleted = dateCompleted;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public List<UserDTO> getUser() {
		return user;
	}

	public void setUser(List<UserDTO> user) {
		this.user = user;
	}

	

}
