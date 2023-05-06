package com.hm.spring.boot.logging.trace.mq.core.producer;


import com.hm.spring.boot.logging.trace.mq.config.annotation.ProducerTraceWrapper;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * JmsTemplateTraceWrapper 发送MQ消息时 自动将 TRACE_ID 放入消息 header 中
 * 
 * @author huwenfeng
 */
@Component
public class JmsTemplateTraceWrapper {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @ProducerTraceWrapper
    public void convertAndSend(String destination, Object data, Map<String, Object> header) {
        jmsMessagingTemplate.convertAndSend(destination, data, header);
    }

}
