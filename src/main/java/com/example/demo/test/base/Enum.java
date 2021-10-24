package com.example.demo.test.base;

import com.example.demo.global.MessageCode;

public class Enum {

	public static void main(String[] args) {
		System.out.println(MessageCode.DEFAULT_ERROR.toString());
		
		System.out.println(MessageCode.valueOf("DEFAULT_ERROR"));
		System.out.println(MessageCode.valueOf("NOT_FOUND"));
	}
}
