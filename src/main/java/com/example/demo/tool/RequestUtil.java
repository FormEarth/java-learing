package com.example.demo.tool;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.example.demo.config.filter.FirstFilter;
import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestUtil {

	public static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * 
	 */
//	public static String getRequestBody_(HttpServletRequest request) throws IOException {
//		BufferedReader reader = request.getReader();
//		StringBuilder sb = new StringBuilder();
//		String line = "";
//		while ((line = reader.readLine()) != null) {
//			sb.append(line);
//		}
//		return sb.toString();
//	}
	
	public static String getRequestBody(HttpServletRequest request) {
		String requestBody = "";
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
	
	public static String getResponseBody(HttpServletResponse response) {
		String responseBody = "";
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

	public static <T extends BaseEntity> T getBody(HttpServletRequest request, Class<T> clazz) throws IOException {

		T object;
		String body = getRequestBody(request);
		if (!"".equals(body) && (Strings.isEmpty(request.getContentType())
				|| request.getContentType().contains(FirstFilter.DEFAULT_CONTENT_TYPE))) {
			object = MAPPER.readValue(body.toString(), clazz);
		} else {
			try {
				object = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				log.error("", e);
				throw new IOException();
			}
		}
		return object;
	}

	public static void main(String[] args) {
		Strings.isEmpty("");
	}
}
