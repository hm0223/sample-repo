package com.hm.spring.boot.logging.trace.mq.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 填充MQ消费者 TRACE_ID
 *
 * @author huwenfeng
 */
@Aspect
@Slf4j
@Component
public class MqConsumerTraceAspect {

    @Pointcut("@annotation(org.springframework.jms.annotation.JmsListener)")
    public void jmsBean() {
    }

    @Before("jmsBean()")
    public void beforeRequest(JoinPoint joinPoint) {
        try {
            final Object[] args = joinPoint.getArgs();
            if (args[0] instanceof Message<?>) {
                Message<?> t = (Message<?>) args[0];
                MDC.put("TRACE_ID", String.valueOf(t.getHeaders().get("TRACE_ID")));
            }
        } catch (Exception exception) {
            log.error("mq trace aspect get trace id error", exception);
        }
    }

}
