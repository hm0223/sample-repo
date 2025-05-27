package com.hm.spring.boot.logging.trace.mq.core.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * MqTraceConsumerSample 包装了TraceId的Mq消费者
 * 
 * @author huwenfeng
 */
@Slf4j
@Component
public class MqTraceConsumerSample implements JmsTemplateTraceConsumerWrapper {

    @JmsListener(destination = "someQueue1")
    @Override
    public void onMessage(Message<?> message) {
        log.info("Rec message >>> \r\n  {}", JSON.toJSONString(message, true));
        log.info("Rec message Payload >>> {}", JSON.toJSONString(message.getPayload(), true));
    }
}

