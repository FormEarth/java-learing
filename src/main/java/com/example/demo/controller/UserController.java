package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

//	@PostMapping(value = "/user/login", name = "登录")
//	void login(@RequestBody User user) {
//		log.info(user.toString());
//	}
}
