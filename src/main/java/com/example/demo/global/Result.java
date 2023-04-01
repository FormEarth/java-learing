package com.example.demo.global;

import java.time.LocalDateTime;

import com.example.demo.tool.MessageUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Response的统一的返回对象
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

	private int code;
	private String message;
//	private String time = Common.DATE_FORMATTER.format(LocalDateTime.now());
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	@DateTimeFormat
	private LocalDateTime time = LocalDateTime.now();
	private T data;

	public Result() {

	}

	/**
	 * 
	 * @param code 响应码
	 * @param message 响应信息
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

	public static <T> Result<T> success() {
		return new Result<>(MessageCode.DEFAULT_SUCCESS);
	}

	public static Result<?> error() {
		return new Result<>(MessageCode.DEFAULT_ERROR);
	}

	public static <T> Result<T> of(MessageCode code) {
		return new Result<>(code);
	}

	public Result<?> setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

}
