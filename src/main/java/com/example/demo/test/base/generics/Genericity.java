package com.example.demo.test.base.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//泛型
public abstract class Genericity<T> {

	/**
	 * 打印泛型对象类型
	 */
	public void printTType(){
//		String type = getClass().getGenericSuperclass().getTypeName();
		Type type = this.getClass().getGenericSuperclass();
//		Type[] type = ;
		Class<T> dc = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		System.out.print(dc.getName());
		System.out.print(dc.getTypeName());
	}

	public abstract void abstractMethod();
}

class Test<E> extends Genericity<E> {

	@Override
	public void abstractMethod() {
		System.out.print("hello world");
	}

	public static void main(String[] args) {
//		Genericity gr = new Test<>();
//		gr.printTType();

		System.out.println("7,8,9".indexOf("7")>=0);
	}
}
