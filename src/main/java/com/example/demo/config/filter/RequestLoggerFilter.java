package com.example.demo.config.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.example.demo.entity.SystemLog;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志记录(Filter) 默认只会记录Content-Type: application/json的请求<br>
 * 其它类型诸如文件上传、下载、WebSocket需自行记录
 * 
 * @author qiyuan
 * @date 2022-1-27 14:07:41
 *
 */
@WebFilter
@Slf4j
public class RequestLoggerFilter extends OncePerRequestFilter {

	/**
	 * 
	 * 获取远程ip
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("{}---begin---------->", this.getClass().getSimpleName());
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
		log.info("{}---end---------->", this.getClass().getSimpleName());
	}

}
