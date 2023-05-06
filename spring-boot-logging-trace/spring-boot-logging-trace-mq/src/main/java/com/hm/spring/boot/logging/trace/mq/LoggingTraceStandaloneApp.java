package com.hm.spring.boot.logging.trace.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * LoggingTraceStandaloneApp.
 *
 * @author huwenfeng
 */
@SpringBootApplication
public class LoggingTraceStandaloneApp {

    public static void main(String[] args) {
        SpringApplication.run(LoggingTraceStandaloneApp.class, args);
    }
}
