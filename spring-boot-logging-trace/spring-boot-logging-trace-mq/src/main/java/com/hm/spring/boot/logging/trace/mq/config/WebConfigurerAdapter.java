package com.hm.spring.boot.logging.trace.mq.config;

import com.hm.spring.boot.logging.trace.mq.core.interceptor.LogTraceInterceptor;
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
    public LogTraceInterceptor logInterceptor() {
        return new LogTraceInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor());
    }
}
