package com.example.demo.test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.entity.User;

public class StreamDemo {

	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User("jack", true));
		users.add(new User("jane", true));
		users.add(new User("admin", false));
		users.add(new User("root", false));
		users.add(new User("test", null));
	}

	/**
	 * 分组
	 */
	private void group() {
		Map<Object, List<User>> tempMap = users.stream().collect(Collectors.groupingBy(x -> x.getGender()));
		System.out.println(tempMap);
	}

	public static void main(String[] args) {
		StreamDemo sd = new StreamDemo();
		sd.group();
	}
}
