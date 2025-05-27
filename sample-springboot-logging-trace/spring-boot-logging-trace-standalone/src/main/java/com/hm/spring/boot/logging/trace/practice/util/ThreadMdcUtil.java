package com.hm.spring.boot.logging.trace.practice.util;

import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * ThreadMdcUtil.
 *
 * @author huwenfeng
 */
public class ThreadMdcUtil {

    private static final String TRACE_ID = "TRACE_ID";

    public static String randomTraceId() {
        return UUID.randomUUID().toString();
    }

    public static void setTraceIdIfAbsent() {
        if (Objects.isNull(MDC.get(TRACE_ID))) {
            MDC.put(TRACE_ID, randomTraceId());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> ctx) {
        return () -> {
            if (Objects.isNull(ctx)) {
                MDC.clear();
            } else {
                MDC.setContextMap(ctx);
            }
            setTraceIdIfAbsent();
            try {
               return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> ctx) {
        return () -> {
            if (Objects.isNull(ctx)) {
                MDC.clear();
            } else {
                MDC.setContextMap(ctx);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
    
}
