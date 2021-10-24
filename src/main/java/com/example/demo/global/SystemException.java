package com.example.demo.global;

public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	public SystemException(MessageCode code) {
		super(code.toString());
		this.code = code.getCode();
	}

	public int getCode() {
		return code;
	}

}
