package com.hm.springboot.logging.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DynamicLoggingController.
 *
 * @author huwenfeng
 */
@Slf4j
@RestController
@RequestMapping("/logging")
public class DynamicLoggingController {

    @GetMapping
    public String index() {
        StringBuilder sb = new StringBuilder();
        if (log.isInfoEnabled()) {
            log.info("echo info log ...");
            sb.append("echo info log ...");
        }
        if (log.isDebugEnabled()) {
            log.debug("echo debug log ...");
            sb.append("echo debug log ...");
        }
        if (log.isErrorEnabled()) {
            log.error("echo error log ...");
            sb.append("echo error log ...");
        }
        return sb.toString();
    }
    
}
