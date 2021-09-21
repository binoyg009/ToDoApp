package com.todo.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name ="todo")

public class Todo {
	


	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name="id")
	private long id ;
	
	@Column(name="todoName")
	private String todoName ;
	
	@Column(name="todoDescription")
	private String todoDescription ;
	
	@DateTimeFormat
	@Column(name="todoDate")
	private LocalDateTime todoDate ;

	
	
	
	public Todo(long id, String todoName, String todoDescription, LocalDateTime todoDate) {
		this.id= id;
		this.todoName= todoName;
		this.todoDescription= todoDescription;	
		this.todoDate= todoDate;	

	}

	public Todo() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public String getTodoDescription() {
		return todoDescription;
	}

	public LocalDateTime getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(LocalDateTime todoDate) {
		this.todoDate = todoDate;
	}

	public void setTodoDescription(String todoDescription) {
		this.todoDescription = todoDescription;
	}
	
	

}
