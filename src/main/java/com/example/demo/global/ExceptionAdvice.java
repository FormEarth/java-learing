package com.example.demo.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(SystemException.class)
	@ResponseStatus
	public Result systemExceptionHandler(SystemException e) {
		return new Result(MessageCode.valueOf(e.getMessage()));
	}

	//@ResponseStatus将http状态码置为失败
	@ExceptionHandler(Exception.class)
	@ResponseStatus
	public Result exceptionHandler(Exception e) {
		log.error("",e);
		return Result.error();
	}

}
