package com.example.demo.test.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author cy.W
 * @date 2022-7-27 10:18:16
 *
 */
public class CollectionDemo {
	
	static void collect(Collection<Object> collect) {
		System.out.println(collect.toString());
	}
	
	public static void main(String[] args) {
		List list = new ArrayList<>();
		collect(list);
		
//		List<String> list1 = new ArrayList<>();
//		collect(list1); 编译不过
	}

}
