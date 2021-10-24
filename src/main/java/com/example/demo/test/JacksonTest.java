package com.example.demo.test;

import java.io.File;
import java.io.IOException;

import com.example.demo.global.MessageCode;
import com.example.demo.global.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	private final static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws IOException {
		
		//{"code":602,"message":"无效的token信息"}
		String string = "{\"code\":602,\"message\":\"无效的token信息\"}";
		Result result = mapper.readValue(string, Result.class);
		System.out.println(result);
		
		Result result1 = new Result(602,"无效的token信息");
//		System.out.println(mapper.writeValueAsString(result1));
		//{"code":602,"message":"无效的token信息","time":"2021-09-14 11:36:06"}
		
		mapper.writeValue(new File("E:\\qiyuan_work\\files\\rest.json"), result1);
	}
}
