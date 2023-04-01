package com.example.demo.global;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 作用在所有注解了@RequestMapping的控制器的方法上
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 这个方法返回true时表示会执行下面的beforeBodyWrite，否则不执行
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

//        System.out.println("----->" + returnType.getParameterType());
        return !returnType.getParameterType().equals(Result.class) && !returnType.getParameterType().equals(ResponseEntity.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        System.out.println("---------------------->beforeBodyWrite");
//		if (body instanceof Result | body instanceof HttpEntity) {
//			System.out.println("----------------------> not handle");
//			return body;
//		} else {
//
//		}
        return Result.success().setData(body);
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus
    public Result<?> systemExceptionHandler(SystemException e) {
        return new Result<>(MessageCode.valueOf(e.getMessage()));
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> notFoundExceptionHandler(NoHandlerFoundException e) {
        return new Result(MessageCode.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<?> notSupportedMethodExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return Result.of(MessageCode.METHOD_NOT_ALLOWED);
    }

    //@ResponseStatus将http状态码置为失败
    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public Result<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
