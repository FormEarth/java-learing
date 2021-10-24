package com.example.demo.test.design_pattern.proxy;

public class SubjectOne implements Subject {

	@Override
	public String doFirst() {
		String str = "first-" + this.getClass().getSimpleName();
		System.out.println(str);
		return str;
	}

}
