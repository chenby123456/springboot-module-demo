package com.cby.config;

import com.cby.handler.ResponseResultHandler;
import com.cby.interceptor.ResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private ResponseInterceptor responseInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseInterceptor);
    }


}
