package com.hm.logging.log4j2.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint.
 *
 * @author huwenfeng
 */
@RestController
@RequestMapping("/endpoint")
@Slf4j
public class Endpoint {
    
    
    @GetMapping("/warn")
    public String testWarn(@RequestParam("msg") String message) {
        log.warn("the warn log is :{}", message);
        return "Success";
    }

    @GetMapping("/error")
    public String testError(@RequestParam("msg") String message) {
        log.error("the error log is :{}", message);
        return "Success";
    }

    @GetMapping("/info")
    public String testInfo(@RequestParam("msg") String message) {
        log.info("the info log is :{}", message);
        return "Success";
    }

    @GetMapping("/debug")
    public String testDebug(@RequestParam("msg") String message) {
        log.debug("the debug log is :{}", message);
        return "Success";
    }
}
