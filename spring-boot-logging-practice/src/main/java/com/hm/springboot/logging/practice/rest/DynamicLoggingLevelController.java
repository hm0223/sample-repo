package com.hm.springboot.logging.practice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DynamicLoggingLevelController.
 *
 * @author huwenfeng
 */
@Slf4j
@RestController
public class DynamicLoggingLevelController {

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
