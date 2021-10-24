package com.example.demo.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;

import com.example.demo.tool.Util;

import lombok.Data;

@Data
public class SystemLog {

	private String id = Util.uuid();

	private String method;
	private String url;
	private String queryString;
	private String requestBody;

	private String reponseBody;
	private Integer code;
	private String message;

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long duration;

	private transient StringBuilder consoleLog;

	public SystemLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime startTime,
			LocalDateTime endTime) {
		this.method = request.getMethod();
		this.url = request.getRequestURL().toString();
		this.queryString = request.getQueryString();
		this.requestBody = Util.getRequestBody(request);
		this.reponseBody = Util.getResponseBody(response);
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = Duration.between(startTime, endTime).toMillis();
	}

	public void print() {
		formatLog();
		System.out.println(consoleLog.toString());
	}

	public void print(Logger log) {
		formatLog();
		log.info(consoleLog.toString());
	}

	private void formatLog() {
		if(consoleLog == null) {
			consoleLog = new StringBuilder();
			consoleLog.append(this.id + "-[url] " + this.url + (Strings.isEmpty(this.queryString) ? "" : "?" + this.queryString)).append("\n");
			consoleLog.append(this.id + "-[request] " + this.requestBody).append("\n");
			consoleLog.append(this.id + "-[response] " + this.reponseBody).append("\n");
			consoleLog.append(this.id
					+ String.format("-[timer] begin_time: %s,end_time: %s,spend: %sms", startTime, endTime, this.duration));
		}
		
	}
}
