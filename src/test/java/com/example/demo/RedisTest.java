package com.example.demo;

import com.example.demo.controller.TestController;
import com.example.demo.global.RedisClientTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Resource(name = "redisClientTest")
	RedisClientTest redisClientTest;
	
	@Test
	public void test11() throws Exception {
//		redisClientTest.addString();
//		redisClientTest.addObjectString();
		//redisClientTest.addHash();
		
		redisClientTest.add_custom();
	}

	@Autowired
	TestController testController;

	@Test
	public void test1() throws Exception {
		System.out.println(testController.getList());
	}
}
