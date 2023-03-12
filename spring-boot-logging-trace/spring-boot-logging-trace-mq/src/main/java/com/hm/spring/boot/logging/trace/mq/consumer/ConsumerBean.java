package com.hm.spring.boot.logging.trace.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.hm.spring.boot.logging.trace.mq.core.ActiveMqTrace;
import com.hm.spring.boot.logging.trace.mq.core.DefaultActiveMqTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerBean {
    
    

    @JmsListener(destination = "someQueue1")
    public void onMessage(Message<?> message) {
        log.info("Rec message:{}", JSON.toJSONString(message));
    }
}

