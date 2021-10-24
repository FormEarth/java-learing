package com.example.demo.test.entity;

import java.time.LocalDateTime;

public class Base {

	public final String BASE = "BASE";
	public String id;
	private LocalDateTime time;
	
	public String getId() {
		return id;
	}
	public Base setId(String id) {
		this.id = id;
		return this;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public Base setTime(LocalDateTime time) {
		this.time = time;
		return this;
	}
	@Override
	public String toString() {
		return "Base [id=" + id + ", time=" + time + "]";
	}
	
	
}
