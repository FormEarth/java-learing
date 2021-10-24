package com.example.demo.test.base;

public class FinalTest {

	public static void main(String[] args) {
		
		String a = "hello2";
		final String b = "hello";
		final String c = hello();
		String d = "hello";
		
		String e = b + 2;
		String f = c + 2;
		String g = d + 2;
		
		System.out.println(a == e);
		System.out.println(a == f);
		System.out.println(a == g);
		
	}
	
	public static String hello() {	
		
		return "hello";
	}
}
