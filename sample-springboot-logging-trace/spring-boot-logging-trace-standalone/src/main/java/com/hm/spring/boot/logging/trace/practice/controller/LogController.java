package com.hm.spring.boot.logging.trace.practice.controller;

import com.hm.spring.boot.logging.trace.practice.service.LogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * LogController.
 *
 * @author huwenfeng
 */
@Slf4j
@AllArgsConstructor
@RestController
public class LogController {

    private final LogService logService;
    
    @GetMapping("/log")
    public void log() {
        log.info("log is start...");
        toLogA();

        logService.asyncLog();
        log.info("log is end...");
    }

    @PostMapping("/log1")
    public void log1(@RequestBody T t) {
        log.info("log is start...");
        System.out.println("t = " + t);
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
