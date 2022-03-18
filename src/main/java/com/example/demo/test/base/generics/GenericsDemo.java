package com.example.demo.test.base.generics;

public class GenericsDemo {

	static <T extends Number & Comparable<T>> T max(T... nums) {
		T max = nums[0];
		for (T temp : nums) {
			max = temp.compareTo(max) >= 0 ? temp : max;
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(max(99, 34, 45, 67));
	}
}
