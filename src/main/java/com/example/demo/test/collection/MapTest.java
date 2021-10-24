package com.example.demo.test.collection;

import java.util.HashMap;

public class MapTest {

	public static void main(String[] args) {
		
//		String str = "I like this apple";
//		System.out.println(split(str));
		int[] num = {1,2,3,4,5};
//		bubbleSort(num);
		int reslut = search(num, 3);
		System.out.println(reslut);
		HashMap<String,String> map = new HashMap<>();
		map.put("123","123");
	}
	
	public static int split(String str) {
		
		String[] words = str.split(" ");
		return words[words.length-1].length();
	}

	//while循环
	public static int search(int[] num,int key) {
		int start = 0;
		int end = num.length-1;
		int middle = (start+end)/2;
		while(start<=end) {
			if(key<num[middle]) {
				end = middle - 1;
			}else if(key>num[middle]) {
				start = middle + 1;
			}else {
				return middle;
			}
		}
		return -1;
	}
	

}
