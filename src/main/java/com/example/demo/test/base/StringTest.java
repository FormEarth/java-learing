package com.example.demo.test.base;

import java.lang.reflect.Field;

public class StringTest {

	static void split() {
		String str = "Hello World!";
		System.out.println(str.toLowerCase());

	}

	static void demo() {
		String str = "Hello World!";
		System.out.println(str.substring(5));// " World!"[]
		System.out.println(str.substring(0, 5));// "Hello"[)
		// System.out.println(str.substring(0,20));//StringIndexOutOfBoundsException
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
		System.out.println(str.startsWith("He"));
		System.out.println(str.endsWith("!"));
		System.out.println(str.compareTo("Hello World"));
	}

	/**
	 * 虚假的交换
	 * 
	 * @param a
	 * @param b
	 */
	static void swap(String a, String b) {
		String temp = a;
		a = b;
		b = temp;
		System.out.println(String.format("%s,%s", a, b));
	}

	/**
	 * 真实的交换
	 * @param a
	 * @param b
	 */
	static void realSwap(String a, String b) {
		String temp = new String(a);
		try {
			Field field = String.class.getDeclaredField("value");
			field.setAccessible(true);
			field.set(a, b.toCharArray());
			field.set(b, temp.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String a = "A";
		String b = "B";
//		swap(a, b);
		realSwap(a, b);
		System.out.println(String.format("%s,%s", a, b));
	}

}
