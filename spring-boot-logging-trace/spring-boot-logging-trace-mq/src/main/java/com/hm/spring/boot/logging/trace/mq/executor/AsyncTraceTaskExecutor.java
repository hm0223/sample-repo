package com.hm.spring.boot.logging.trace.mq.executor;

import com.hm.spring.boot.logging.trace.mq.util.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * AsyncTraceTaskExecutor.
 *
 * @author huwenfeng
 */
public final class AsyncTraceTaskExecutor extends ThreadPoolTaskExecutor {
    
    public AsyncTraceTaskExecutor() {
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
