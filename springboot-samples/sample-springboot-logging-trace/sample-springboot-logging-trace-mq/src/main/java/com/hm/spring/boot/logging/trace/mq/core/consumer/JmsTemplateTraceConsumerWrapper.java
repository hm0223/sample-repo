
package com.hm.spring.boot.logging.trace.mq.core.consumer;

import org.springframework.messaging.Message;

/**
 * JmsTemplateTraceConsumerWrapper 
 * 
 * @author huwenfeng
 */
public interface JmsTemplateTraceConsumerWrapper {

    void onMessage(Message<?> message);
}

