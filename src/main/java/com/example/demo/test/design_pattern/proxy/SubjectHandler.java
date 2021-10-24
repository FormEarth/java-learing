package com.example.demo.test.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectHandler implements InvocationHandler {

	private Object subject;

	public SubjectHandler(Object subject) {
		this.subject = subject;
	}
	
	public Object instance() {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                this.subject.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj = method.invoke(subject, args);
		return obj;
	}
	
	public static void main(String[] args) {
		
		Subject subject = (Subject) new SubjectHandler(new SubjectOne()).instance();
		subject.doFirst();
		
	}

}
