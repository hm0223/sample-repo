package com.hm.spring.boot.logging.trace.mq.controller;

import com.alibaba.fastjson2.JSON;
import com.hm.spring.boot.logging.trace.mq.core.producer.JmsTemplateTraceWrapper;
import com.hm.spring.boot.logging.trace.mq.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * LogController.
 *
 * @author huwenfeng
 */
@Slf4j
@RestController
public class LogController {

    @Resource
    private LogService logService;
    @Resource
    private JmsTemplateTraceWrapper jmsTemplateWrapper;
    

    @GetMapping("/log")
    public void log() {
        log.info("log is start...");
        toLogA();

        Map<String, Object> map = new HashMap<>();
        map.put("Name", "Mark");
        map.put("Age", 47);
        String message = JSON.toJSONString(map);
        jmsTemplateWrapper.convertAndSend("someQueue1", message, new HashMap<>());
        logService.asyncLog();
        log.info("log is end...");
    }

    private void toLogA() {
        log.info("logA is start...");
        toLogB();
        log.info("logA is end...");
    }

    private void toLogB() {
        log.info("logB is start...");
        log.info("logB is end...");
    }

}
