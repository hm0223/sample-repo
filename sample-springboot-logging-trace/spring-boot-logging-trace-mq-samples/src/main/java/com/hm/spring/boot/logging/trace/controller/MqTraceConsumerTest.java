package com.hm.spring.boot.logging.trace.controller;

import com.alibaba.fastjson.JSON;
import com.hm.spring.boot.logging.trace.mq.core.consumer.JmsTemplateTraceConsumerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * MqTraceConsumerTest 包装了TraceId的Mq消费者
 * 
 * @author huwenfeng
 */
@Slf4j
@Component
public class MqTraceConsumerTest implements JmsTemplateTraceConsumerWrapper {

    public static final String QUEUE_NAME = "MQ_TRACE_TEST";

    @JmsListener(destination = QUEUE_NAME)
    @Override
    public void onMessage(Message<?> message) {
        log.info("Rec message >>> \r\n  {}", JSON.toJSONString(message, true));
        log.info("Rec message Payload >>> {}", JSON.toJSONString(message.getPayload(), true));
    }
}

