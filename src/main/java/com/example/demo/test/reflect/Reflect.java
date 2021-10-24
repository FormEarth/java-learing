package com.example.demo.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.test.entity.Element;

public class Reflect {
	
	static ConcurrentHashMap<String, Field[]> cache = new ConcurrentHashMap<>();

	static String firstCharUpper(String str) {
		if (str == null || str.equals(""))
			return "";
		String firstChar = String.valueOf(str.charAt(0)).toUpperCase();
		return firstChar + str.substring(1, str.length());
	}

	/**
	 * ��ȡָ��������������ԣ����������г���
	 * @param object
	 * @return
	 */
	static Field[] getAllFields(Object object) {

		Class<?> clazz = object.getClass();
		String typeName = clazz.getName();
		if(cache.containsKey(typeName)) {
			return cache.get(typeName);
		}
		Field[] fields = new Field[0];
		while (clazz != Object.class) {
			int last_length = fields.length;
			Field[] temp = clazz.getDeclaredFields();
			if(temp.length >0) {
				fields = Arrays.copyOf(fields, last_length + temp.length);
				System.arraycopy(temp, 0, fields, last_length, temp.length);
			}
			clazz = clazz.getSuperclass();
		}
		cache.put(typeName, fields);
		return fields;
	}

	public static Map<String, Object> parseObject2Map(Object obj) throws IllegalAccessException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (Field field : getAllFields(obj)) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			System.out.println(String.format("%s %s: %s", Modifier.toString(field.getModifiers()), field.getName(),
					field.get(obj)));
			String fieldName = field.getName();
			Object value = field.get(obj);
			map.put(fieldName, value);
		}
		System.out.println(map);
		return map;
	}

	public static void parseMap2Object(HashMap<String, Object> map, Object object) {
		if (map == null || map.isEmpty())
			return;
		for (Field field : getAllFields(object)) {
			try {
				if (map.containsKey(field.getName())) {
					field.setAccessible(true);
					field.set(object, map.get(field.getName()));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		System.out.println(object);
	}

	public static void main(String[] args) throws IllegalAccessException {
		// Element element = new
		// Element("div").setHeight(100).setWidth(200).setZIndex(123);
		// element.setId("001");
		// element.setTime(LocalDateTime.now());
		// parseObject2Map(element);

//		HashMap<String, Object> map = new HashMap<>();
//		map.put("name", "div");
//		map.put("id", "100");
//		map.put("zIndex", 100);
//		map.put("display", "none");
//		Element element2 = new Element();
//		parseMap2Object(map, element2);
		Element element2 = new Element();
		System.out.println(element2.getClass().getTypeName());
		System.out.println(element2.getClass().getName());
	}

}
