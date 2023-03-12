package com.hm.spring.boot.logging.trace.mq.core;

/**
 * MQ消息接收方追踪
 * 
 * @author huwenfeng
 */
public interface MqReceiveTrace<T> {

    void onMessage(T t);

}
