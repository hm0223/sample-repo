package com.hm.spring.boot.logback.practice.service.impl;

import com.hm.spring.boot.logback.practice.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * LogServiceImpl.
 *
 * @author huwenfeng
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Async("asyncExecutor")
    @Override
    public void asyncLog() {
        log.info("asyncLog is start now...");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("asyncLog is end now...");
    }
}
