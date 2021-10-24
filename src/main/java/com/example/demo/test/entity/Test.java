package com.example.demo.test.entity;

public class Test {

	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = "abc";
		String str3 = new String("abc");

		System.out.println(str1 == str2);// true
		System.out.println(str1 == str3);// false
		System.out.println(str1.equals(str2));// true
		System.out.println(str1.equals(str3));// true

		int x = 1, y = 1;
		if (x++ == 2 & ++y == 2) {//i++ 先运算再赋值;++i 先赋值在运算
			x = 7;
		}
		System.out.println("x=" + x + ",y=" + y);

	}

}
