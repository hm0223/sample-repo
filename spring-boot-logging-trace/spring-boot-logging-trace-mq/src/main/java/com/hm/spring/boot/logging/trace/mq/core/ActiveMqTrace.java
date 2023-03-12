package com.hm.spring.boot.logging.trace.mq.core;


import org.slf4j.MDC;
import org.springframework.messaging.Message;

/**
 * @author huwenfeng
 */
public abstract class ActiveMqTrace implements MqSendTrace, MqReceiveTrace<Message<?>> {

    @Override
    public void onMessage(Message<?> t) {
        if (t.getHeaders() != null) {
            MDC.put("TRACE_ID", String.valueOf(t.getHeaders().get("TRACE_ID")));
        }
    }

    @Override
    public void convertAndSend(String destination, Object message) {
        throw new UnsupportedOperationException();
    }
}
