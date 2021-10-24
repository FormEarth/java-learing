package com.example.demo.global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.demo.tool.MessageUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

	private static transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private int code;
	private String message;
	private String time = formatter.format(LocalDateTime.now());
	private Object data;

	public Result() {

	}

	/**
	 * 
	 * @param code
	 * @param message
	 * @deprecated
	 */
	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Result(MessageCode code) {
		this.code = code.getCode();
		this.message = MessageUtil.get(String.valueOf(this.code));
	}

	public static Result success() {
		return new Result(MessageCode.DEFAULT_SUCCESS);
	}

	public static Result error() {
		return new Result(MessageCode.DEFAULT_ERROR);
	}

	public Result setCode(int code) {
		this.code = code;
		return this;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}

}
