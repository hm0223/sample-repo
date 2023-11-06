package com.hm.geek.beauty.design.ch13_ch14.config;

import com.hm.geek.beauty.design.ch13_ch14.security.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * SecurityMvcConfigurationSupport.
 * 
 * @author huwenfeng
 */
@Configuration
public class SecurityMvcConfigurationSupport implements WebMvcConfigurer {

    @Resource
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**");
    }
}