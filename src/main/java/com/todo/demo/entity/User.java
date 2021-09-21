package com.todo.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="user")

public class User {

	@Id
	@Column(name="name")
	private String name ;
	
	@Column(name="password")
	private String password ;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User() {
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
}
