package com.example.demo.global;

import java.time.format.DateTimeFormatter;

/**
 * 	全局静态变量
 * @author qiyuan
 * @date 2022-1-27 11:16:34
 *
 */
public class Common {

	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
}
