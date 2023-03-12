package com.hm.spring.boot.logging.trace.mq.config;


import com.hm.spring.boot.logging.trace.mq.executor.AsyncTraceTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * ThreadPoolConfig.
 *
 * @author huwenfeng
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        AsyncTraceTaskExecutor executor = new AsyncTraceTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("customize-t");
        executor.initialize();
        return executor;
    }
}
