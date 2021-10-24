package com.example.demo.config.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.example.demo.entity.SystemLog;

@WebFilter
public class SecondFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println(this.getClass().getSimpleName() + "---begin---------->");
		LocalDateTime startTime = LocalDateTime.now();
		if (!(request instanceof ContentCachingRequestWrapper)) {
			request = new ContentCachingRequestWrapper(request);
		}
		if (!(response instanceof ContentCachingResponseWrapper)) {
			response = new ContentCachingResponseWrapper(response);
		}
		filterChain.doFilter(request, response);
		LocalDateTime endTime = LocalDateTime.now();

		SystemLog systemLog = new SystemLog(request, response, startTime, endTime);
		systemLog.print();
		// 将流重新放入response
		ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		Objects.requireNonNull(responseWrapper).copyBodyToResponse();
		System.out.println(this.getClass().getSimpleName() + "---end---------->");
	}


}
