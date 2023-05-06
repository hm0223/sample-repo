package com.hm.spring.boot.logging.trace.mq.config.aspect;

import com.hm.spring.boot.logging.trace.mq.core.TraceConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 填充MQ消费者 TRACE_ID
 *
 * @author huwenfeng
 */
@Aspect
@Slf4j
@Component
public class MqProducerTraceAspect {

    @Pointcut("@annotation(com.hm.spring.boot.logging.trace.mq.config.annotation.ProducerTraceWrapper)")
    public void jmsProducerBean() {
    }

    @Before("jmsProducerBean()")
    public void beforeRequest(JoinPoint joinPoint) {
        try {
            final Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Map) {
                    @SuppressWarnings("unchecked") Map<String, Object> head = (Map<String, Object>) arg;
                    head.put(TraceConstant.TRACE_ID, MDC.get(TraceConstant.TRACE_ID));
                }
            }
        } catch (Exception exception) {
            log.error("mq trace aspect put trace id error", exception);
        }
    }

}
