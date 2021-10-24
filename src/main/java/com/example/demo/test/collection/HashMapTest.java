package com.example.demo.test.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Nidhogg
 * @date 2018年10月24日
 */
public class HashMapTest {

    private static HashMap<String, String> map = new HashMap<>();

    static {
        map.put("aaa", "AAA");
        map.put("bbb", "BBB");
        map.put("ccc", "CCC");
    }

    /**
     * 遍历Map的方法
     **/
    public static void traverseMap1() {
        for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
        }
		System.out.println("-----------------------------");
    }

    /**
     * 和第一种一样(这里会出错，请比对它和下面的方式差别)
     **/
    public static void traverseMap2() {
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
			String key = iterator.next().getKey();
			String value = iterator.next().getValue();
			System.out.println(key + "," + value);
        }
		System.out.println("-----------------------------");
    }

	/**
	 * 和第一种一样
	 **/
	public static void traverseMap22() {
		Iterator<Entry<String,String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String,String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
		}
		System.out.println("-----------------------------");
	}

    /**
     * 先遍历key，再根据key获取value，效率较差
     **/
    public static void traverseMap3() {
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
			System.out.println(key + "," + value);
        }
		System.out.println("-----------------------------");
    }

    /**
     * lambda表达式
     **/
    public static void traverseMap4() {
        map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "," + e.getValue()));
    }

    public static void main(String[] args) {

        traverseMap1();
        traverseMap22();
        traverseMap3();
        traverseMap4();

    }

}
