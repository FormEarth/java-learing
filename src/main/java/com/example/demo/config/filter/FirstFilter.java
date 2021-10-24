package com.example.demo.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;

@WebFilter
public class FirstFilter implements Filter {

	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	private static final String DEFAULT_CHARACTER = "utf-8";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(this.getClass().getSimpleName() + "---begin---------->");
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		if(Strings.isEmpty(contentType) || contentType.contains(DEFAULT_CONTENT_TYPE)) {
			response.setContentType(DEFAULT_CONTENT_TYPE);
			response.setCharacterEncoding(DEFAULT_CHARACTER);
		}
		
		chain.doFilter(request, response);
		System.out.println(this.getClass().getSimpleName() + "---end---------->");
	}

	
}
