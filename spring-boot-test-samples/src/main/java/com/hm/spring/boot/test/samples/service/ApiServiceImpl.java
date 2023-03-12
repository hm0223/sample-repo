package com.hm.spring.boot.test.samples.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ApiServiceImpl.
 *
 * @author huwenfeng
 */
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    private ApiService2 apiService2;
    
    @Override
    public String api1() {
        log.info("api1 is start!");
        return "api1:" + apiService2.api2();
    }
}
