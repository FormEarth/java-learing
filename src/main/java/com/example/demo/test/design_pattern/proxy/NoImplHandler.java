package com.example.demo.test.design_pattern.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class NoImplHandler implements InvocationHandler {

	HashMap<String, String> cache = new HashMap<>();

	public Object instance(String str) throws IOException, ClassNotFoundException {

		InputStream in = Subject.class.getResourceAsStream("proxy.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		if (reader.ready()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(":");
				if (temp.length != 2)
					break;
				cache.put(temp[0].trim(), temp[1].trim());
//				System.out.println(line);
			}
		}

		Class<?> clazz = Class.forName(cache.get("interface"));
		Class<?>[] interfaces = new Class[] { clazz };
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// do noting
		// proxy.getClass().getName();
		method.getReturnType();
		System.out.println("==> " + method.getName());
		if (cache.containsKey(method.getName())) {
			return cache.get(method.getName());
		}
		return "success";
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Subject subject = (Subject) new NoImplHandler().instance(null);
		System.out.println(subject.doFirst());
		
	}

}
