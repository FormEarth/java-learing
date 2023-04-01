package com.example.demo.global;
/**
 * 定义了所有需要国际化的消息码值
 * @see /src/main/resources/static/i18n
 * @author Qiyuan
 *
 */
public enum MessageCode {

	DEFAULT_SUCCESS(200),
	NOT_FOUND(404),
	METHOD_NOT_ALLOWED(405),
	DEFAULT_ERROR(500),
	SERVER_REFUSE(501)
	;
	
	private int code;
	MessageCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
