package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.global.Result;

@RestController
public class TestController {
	
	//当未指明请求方法时，则所有的方法都可以请求
	@RequestMapping(value = "/nomethod")
	public Result nomethod() {
		return Result.success();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Result index() {
		return Result.success();
	}

	@RequestMapping(value = "/test/list", method = RequestMethod.GET)
	public List<String> getList() {
		return Arrays.asList("aa", "bb", "cc", "dd");
	}

	@RequestMapping(value = { "/test/str", "/test/string" }, method = RequestMethod.GET)
	public Object getString() {
		return "aa,bb,cc,dd";
	}

	@RequestMapping(value = "/test/map", method = RequestMethod.GET)
	public Map<String, String> getMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "aaa");
		map.put("2", "bbb");
		map.put("3", "ccc");
		return map;
	}

	@RequestMapping(value = "/test/except", method = RequestMethod.GET)
	public long except(@RequestParam String name, @RequestParam long number) {
		System.out.println("name:" + name + " number:" + number);
		return 100 / number;
	}
	
	@PostMapping(value = "/save/content")
	public void post(@RequestBody HashMap<String, String> params) {
		System.out.println(params);
		
	}

}
