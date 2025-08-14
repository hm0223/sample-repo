package com.hm.spring.boot.logging.trace.practice.executor;

import com.hm.spring.boot.logging.trace.practice.util.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * CustomizeAsyncThreadPoolTaskExecutor.
 *
 * @author huwenfeng
 */
public final class CustomizeAsyncThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    
    public CustomizeAsyncThreadPoolTaskExecutor() {
        super();
    }

    @Override
    public void execute(Runnable command) {
        super.execute(ThreadMdcUtil.wrap(command, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }
}
