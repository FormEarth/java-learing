package com.example.demo.test.base;

public class StringTest {

	static void split(){
		String str = "Hello World!";
		System.out.println(str.toLowerCase());

	}

	public static void main(String[] args) {
		String str = "Hello World!";
		System.out.println(str.substring(5));//" World!"[]
		System.out.println(str.substring(0,5));//"Hello"[)
		//System.out.println(str.substring(0,20));//StringIndexOutOfBoundsException
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
		System.out.println(str.startsWith("He"));
		System.out.println(str.endsWith("!"));
		System.out.println(str.compareTo("Hello World"));
	}
}
