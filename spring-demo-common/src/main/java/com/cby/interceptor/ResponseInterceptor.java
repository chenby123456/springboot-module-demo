package com.cby.interceptor;

import com.cby.annotation.ResponseResultAnnotation;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ResponseInterceptor implements HandlerInterceptor {
    private final static String RESPONSE_RESULT_ANNO="RESPONSE_RESULT_ANNO";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod)handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if(clazz.isAnnotationPresent(ResponseResultAnnotation.class)){
                request.setAttribute(RESPONSE_RESULT_ANNO,clazz.getAnnotation(ResponseResultAnnotation.class));
            }else if(method.isAnnotationPresent(ResponseResultAnnotation.class)){
                request.setAttribute(RESPONSE_RESULT_ANNO,method.getAnnotation(ResponseResultAnnotation.class));
            }
        }
        return true;
    }


}
