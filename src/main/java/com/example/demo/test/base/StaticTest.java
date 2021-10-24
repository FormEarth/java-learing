package com.example.demo.test.base;

/**
 * static关键字
 *
 * @author raining_heavily
 * @date 2019/12/11 19:14
 **/
public class StaticTest {
	private String name;
	private static String something;

	 static {
		something = "hello world!";
		System.out.println("this is static code block!");
	}

	 {
		this.name = "my name is Jane";
		System.out.println("this is not-static code block!");
	}

	static void doSomething() {
		System.out.println("do something");
	}

	void doOtherSomething() {
		System.out.println(this.name);
		System.out.println("do something");
	}

	public static void main(String[] args) {
		System.out.println("main method running!");
        System.out.println(StaticTest.something);
        StaticTest.doSomething();
		StaticTest st = new StaticTest();
		st.doOtherSomething();
	}
}
