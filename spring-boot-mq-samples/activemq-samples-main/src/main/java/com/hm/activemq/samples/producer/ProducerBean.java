package com.hm.activemq.samples.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerBean implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    public ProducerBean(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void someMethod() {
        this.jmsTemplate.convertAndSend("someQueue", "hello");
    }

    @Override
    public void run(String... args) throws Exception {
        someMethod();
    }
}

