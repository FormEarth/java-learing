package com.example.demo.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.demo.entity.SystemInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class AccessControl {

	private final static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	WebApplicationContext applicationContext;

	public @PostConstruct void apiCheck() {

		System.out.println("begin ===========>" + this.getClass().getSimpleName());
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
		List<SystemInterface> apis = new ArrayList<>();
		for (RequestMappingInfo info : methodMap.keySet()) {
			Set<String> urlSet = info.getPatternsCondition().getPatterns();
			if (urlSet.size() > 0) {
				ArrayList<String> list = new ArrayList<>(urlSet);
				if (list.get(0).equals("/error")) {
					continue;
				}
			}
			// 获取全部请求方式
			Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
			if (methods.size() == 0) {
				System.err.println(urlSet.toString() + " request method is undefined");
			}
			String m = "";
			for (RequestMethod method : methods) {
				m = method.toString();
				break;
			}
			for (String url : urlSet) {
				SystemInterface i = new SystemInterface();
				i.setPath(url);
				i.setMethod(m);
				i.setName(info.getName());
				i.setPermission(new HashSet<>());
//				System.out.println(i.toString());
				apis.add(i);
			}
		}
		try {
			mapper.writeValue(new File("E:\\qiyuan_work\\files\\apis.json"), apis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
