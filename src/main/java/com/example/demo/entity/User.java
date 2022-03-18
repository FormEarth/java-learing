package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class User extends BaseEntity  {

	private String name;
	private String password;
	private String mail;
	private Boolean gender;
	
	public User() {
		
	}
	
	public User(String name,Boolean gender) {
		this.name = name;
		this.gender = gender;
	}
}
