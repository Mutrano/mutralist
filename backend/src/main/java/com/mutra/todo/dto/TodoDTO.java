package com.mutra.todo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.mutra.todo.domain.Todo;
import com.mutra.todo.domain.User;

public class TodoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private LocalDate startDate;
	private LocalDate dueDate;
	private User user;
	
	public TodoDTO() {
	}

	public TodoDTO(Todo obj) {
		this.id = obj.getId();
		this.text = obj.getText();
		this.startDate = obj.getStartDate();
		this.dueDate = obj.getDueDate();
		this.user = obj.getUser();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
