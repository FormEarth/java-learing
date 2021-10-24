package com.example.demo.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.test.entity.Element;


public class ReflectTest {

	/**
	 * 获取指定对象的全部属性(包含所有超类)
	 * 
	 * @param object
	 * @return
	 */
	static Field[] getAllFields(Object object) {
		Class<?> clazz = object.getClass();
		Field[] all = new Field[0];
		while (clazz != Object.class) {
			int last_length = all.length;// 之前一次的数组长度
			Field[] current = clazz.getDeclaredFields();
			if (current.length > 0) {
				all = Arrays.copyOf(all, last_length + current.length);// 扩容原数组
				System.arraycopy(current, 0, all, last_length, current.length);// 为扩容后的元素赋值
			}
			clazz = clazz.getSuperclass();
		}
		return all;
	}

	public static HashMap<String, Object> getMapbyObject(Object object) {

		HashMap<String, Object> map = new HashMap<>();
		for (Field field : getAllFields(object)) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(object));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static void getObjectbyMap(Map<String, Object> map, Object object) {

		Field[] fields = getAllFields(object);
		if (fields.length < 1)
			return;
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			if (map.containsKey(field.getName())) {
				try {
					field.set(object, map.get(field.getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println(object);
	}

	public static void main(String[] args) {
//		Element element = new Element("div");
//		element.setHeight(100).setHeight(200).setId("100");
//		System.out.println("map" + getMapbyObject(element));
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("name","span");
		map.put("id","007");
		map.put("width",100);
		map.put("color","#fff");
		map.put("display","none");
		getObjectbyMap(map,new Element("div"));
	}
}
