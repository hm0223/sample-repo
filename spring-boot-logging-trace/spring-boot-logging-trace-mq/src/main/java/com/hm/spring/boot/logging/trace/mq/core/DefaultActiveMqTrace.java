package com.hm.spring.boot.logging.trace.mq.core;


import org.slf4j.MDC;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author huwenfeng
 */
@Component
public class DefaultActiveMqTrace extends ActiveMqTrace {

    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void convertAndSend(String destination, Object data) {
        jmsTemplate.convertAndSend(destination, data, message -> {
            message.setStringProperty(Trace.TRACE_ID, MDC.get(Trace.TRACE_ID));
            message.setJMSCorrelationID(MDC.get(Trace.TRACE_ID));
            return message;
        });
    }

}
