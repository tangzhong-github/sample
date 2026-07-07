package com.tangzhong.sample.framework.web.config;

import com.tangzhong.sample.framework.web.interceptor.TraceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tangzhong
 * @date   2026-07-03 10:16
 * @since  V1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceInterceptor()).addPathPatterns("/**");
    }

}