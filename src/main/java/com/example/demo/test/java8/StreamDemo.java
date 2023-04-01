package com.example.demo.test.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.example.demo.entity.User;

public class StreamDemo {

	private static final List<User> users = new ArrayList<User>();

	static {
		users.add(new User("jack", true, 10));
		users.add(new User("jane", true, 10));
		users.add(new User("admin", false, 20));
		users.add(new User("root", false, 20));
		users.add(new User("test", null, 15));
		users.add(new User(null, null, 15));
	}

	private void basic() {
		// 过滤
		users.stream().filter(User::getGender).collect(Collectors.toList());
		// 遍历计算
		users.stream().map(User::getName).forEach(System.out::println);
		// 排序
		users.stream().sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);
	}

	/**
	 * 分组
	 */
	private void group() {
		Map<Object, List<User>> tempMap = users.stream().collect(Collectors.groupingBy(x -> x.getGender()));
		System.out.println(tempMap);
	}

	/**
	 * 排序
	 */
	private void sortAdvance() {
		// 直接排序包含null的字段时会 NPE
		users.stream().sorted(Comparator.comparing(User::getName).reversed()).forEach(System.out::println);
		// 多字段排序
		users.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge)).forEach(System.out::println);
	}

	public static void main(String[] args) {
		StreamDemo sd = new StreamDemo();
//		sd.group();
		sd.sortAdvance();
	}
}
