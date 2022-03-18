package com.example.demo.entity;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.example.demo.tool.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SystemLog {

	private String id = Util.uuid();

	private String method;
	private String url;
	// 查询字符串，即url上携带的参数
	private String queryString;
	private Integer code;
	private String message;

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long duration;
	private String ip;

	private transient String requestBodyStr;
	private transient String reponseBodyStr;
	private transient StringBuilder consoleLog;

	public SystemLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime startTime,
			LocalDateTime endTime) {
		this.method = request.getMethod();
		this.url = request.getRequestURL().toString();
		this.queryString = request.getQueryString();
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = Duration.between(startTime, endTime).toMillis();
		this.ip = request.getRemoteHost();
		this.requestBodyStr = getRequestBody(request);
		this.reponseBodyStr = getResponseBody(response);
	}

	/**
	 * @deprecated
	 */
	public void print() {
		formatLog();
		System.out.println(consoleLog.toString());
	}

	public void print(Logger log) {
		formatLog();
		log.info(consoleLog.toString());
	}

	private void formatLog() {

		if (consoleLog == null) {
			consoleLog = new StringBuilder();
			consoleLog.append(this.id + "-[url][" + this.method + "] " + this.url
					+ (Strings.isEmpty(this.queryString) ? "" : "?" + this.queryString)).append("\n");
			consoleLog.append(this.id + "-[request] " + this.requestBodyStr).append("\n");
			consoleLog.append(this.id + "-[response] " + this.reponseBodyStr).append("\n");
			consoleLog.append(this.id + String.format("-[timer] begin_time: %s,end_time: %s,spend: %sms", startTime,
					endTime, this.duration));
		}

	}

	// 是否忽略记录请求日志
	// 预检请求、WebSocket请求、带二进制流的请求、包含敏感字段的请求
	boolean ignoreRequest(HttpServletRequest request) {
		// 预检请求（OPTIONS）
		if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getContentType())) {
			return true;
		}
		// 非application/json请求
		if (!request.getContentType().toLowerCase().contains("application/json")) {
			return true;
		}
		return false;
	}

	// 是否忽略记录响应日志
	// 带二进制流的响应
	boolean ignoreResponse(HttpServletResponse response) {
		// 非application/json响应
		if (!response.getContentType().toLowerCase().contains("application/json")) {
			return true;
		}
		return false;
	}

	private String getRequestBody(HttpServletRequest request) {
		String requestBody = "";
		if (this.ignoreRequest(request)) {
			return requestBody;
		}
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			try {
				requestBody = IOUtils.toString(wrapper.getContentAsByteArray(), "utf-8");
			} catch (IOException e) {
				// NOOP
				log.error("get requestbody failed", e);
			}
		}
		return requestBody;
	}

	private String getResponseBody(HttpServletResponse response) {
		String responseBody = "";
		if (this.ignoreResponse(response)) {
			return responseBody;
		}
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		if (wrapper != null) {
			try {
				responseBody = IOUtils.toString(wrapper.getContentAsByteArray(), "utf-8");
			} catch (IOException e) {
				// NOOP
				log.error("get response body failed", e);
			}
		}
		return responseBody;
	}
}
