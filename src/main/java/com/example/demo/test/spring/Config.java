package com.example.demo.test.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.example.demo.test.entity.User;

@Configuration
public class Config {
	
	@Bean
	@Lazy
	@Scope("prototype")
	public User user() {
		return new User("001", "jan", "2021/10/26", "male");
	}

}
