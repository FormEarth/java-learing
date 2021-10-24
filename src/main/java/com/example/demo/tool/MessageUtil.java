package com.example.demo.tool;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

	private static MessageSource messageSource;

	public MessageUtil(MessageSource messageSource) {
		MessageUtil.messageSource = messageSource;
	}

	public static String get(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

}
