package com.hm.spring.boot.logging.trace.mq.core;

/**
 * MQ消息发送方追踪
 * 
 * @author huwenfeng
 */
public interface MqSendTrace {
    void convertAndSend(String destination, Object message);

}
