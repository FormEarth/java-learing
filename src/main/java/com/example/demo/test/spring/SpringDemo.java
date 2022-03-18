package com.example.demo.test.spring;

import java.util.Arrays;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

	void startContext() {
		
		// 基于配置类启动spring容器
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Config.class);
		// 基于配置文件启动spring容器
//		ClassPathXmlApplicationContext context = 
//				new ClassPathXmlApplicationContext("classpath:/com/example/demo/test/spring/spring-config.xml");
		context.start();
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		System.out.println(beanFactory.getBean("user"));
		BeanDefinition definition = beanFactory.getBeanDefinition("user");
		System.out.println(definition);
		System.out.println(beanFactory.getBeanDefinitionCount());
		System.out.println(Arrays.asList(beanFactory.getBeanDefinitionNames()));
		beanFactory.getBeanNamesIterator().forEachRemaining(item -> System.out.println(item));
		context.close();
	}
	
	public static void main(String[] args) {

		SpringDemo demo = new SpringDemo();
		demo.startContext();
	}

}
