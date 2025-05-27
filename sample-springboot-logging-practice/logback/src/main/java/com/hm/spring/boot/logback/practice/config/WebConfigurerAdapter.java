package com.hm.spring.boot.logback.practice.config;

import com.hm.spring.boot.logback.practice.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfigurerAdapter.
 *
 * @author huwenfeng
 */
@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor());
    }
}
