package com.example.demo.entity;

import java.util.Set;

import lombok.Data;

@Data
public class SystemInterface {
	
	private String path;
	private String method;
	private String name;
	private Boolean requireLogin;
	private Set<String> permission ;

}
