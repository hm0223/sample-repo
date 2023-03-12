package com.hm.spring.boot.logging.trace.mq.producer;

import com.alibaba.fastjson2.JSON;
import com.hm.spring.boot.logging.trace.mq.core.ActiveMqTrace;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProducerBean implements CommandLineRunner {

    @Resource
    private ActiveMqTrace activeMqTrace;

    public void someMethod() {
        sendWithConversion();
    }
    public void sendWithConversion() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", "Mark");
        map.put("Age", 47);
        String message = JSON.toJSONString(map);
        activeMqTrace.convertAndSend("someQueue1", message);
    }


    @Override
    public void run(String... args) throws Exception {
        someMethod();
    }

}

