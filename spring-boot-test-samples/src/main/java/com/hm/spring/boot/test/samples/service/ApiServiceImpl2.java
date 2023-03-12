package com.hm.spring.boot.test.samples.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ApiServiceImpl.
 *
 * @author huwenfeng
 */
@Slf4j
@Service
public class ApiServiceImpl2 implements ApiService2 {
    @Override
    public String api2() {
        log.info("api2 is start!");
        return "api2";
    }
}
