package com.example.demo.test.base;

import com.example.demo.test.util.Util;
import org.jetbrains.annotations.NotNull;

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

	// 将下划线风格的字符串转为驼峰风格
	public static String underlineToCamel(@NotNull String str) {

		StringBuilder sb = new StringBuilder();
		String[] words = str.split("_");
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (i == 0) {
				sb.append(word);
			} else {
				sb.append(Character.toUpperCase(word.charAt(0)));
				sb.append(word.substring(1));
			}
		}
		System.out.println(sb);
		return sb.toString();
	}


	public static void main(String[] args) {
//		String a = "A";
//		String b = "B";
////		swap(a, b);
//		realSwap(a, b);
//		System.out.println(String.format("%s,%s", a, b));
//		String str = "op=1&sn=SN000222&pakgesum=3&index=1&datalen=1046&data=7889";
//		int i = str.lastIndexOf("=");
//		System.out.println(str.substring(i));
//		System.out.println(str.substring(i));

		underlineToCamel("rtte_hgrh");
	}

}
