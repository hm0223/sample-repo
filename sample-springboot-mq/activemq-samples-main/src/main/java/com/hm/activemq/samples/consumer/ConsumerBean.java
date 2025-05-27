package com.hm.activemq.samples.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerBean {

    @JmsListener(destination = "someQueue")
    public void processMessage(String content) {
        // ...
        System.out.println("Rec: content = " + content);
    }

}

