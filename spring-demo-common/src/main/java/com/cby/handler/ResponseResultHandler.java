package com.cby.handler;

import com.cby.annotation.ResponseResultAnnotation;
import com.cby.dto.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice {
    private final static String RESPONSE_RESULT_ANNO="RESPONSE_RESULT_ANNO";

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseResultAnnotation responseResult = (ResponseResultAnnotation) request.getAttribute(RESPONSE_RESULT_ANNO);
        return responseResult == null?false:true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseResult){
           return body;
        }
        return ResponseResult.ok(body);
    }
}
