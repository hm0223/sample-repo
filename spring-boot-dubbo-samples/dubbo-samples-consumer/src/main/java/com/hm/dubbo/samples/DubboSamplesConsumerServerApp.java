package com.hm.dubbo.samples;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * DubboSamplesConsumerServerApp.
 *
 * @author huwenfeng
 */
@EnableDubbo
@SpringBootApplication
@ComponentScan(basePackages = "com.hm")
public class DubboSamplesConsumerServerApp {

    public static void main(String[] args) {
        SpringApplication.run(DubboSamplesConsumerServerApp.class, args);
    }
}
