package com.qa.dto;

import java.util.Date;
import java.util.List;

public class TodoDTO {

	private int id;

	private String title;

	private String memo;

	private boolean important;

	public TodoDTO() {
		super();
	}

	public TodoDTO(int id, String title, String memo, boolean important) {
		super();
		this.id = id;
		this.title = title;
		this.memo = memo;
		this.important = important;
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

}
