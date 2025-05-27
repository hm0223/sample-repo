package com.hm.dubbo.samples.service;

import com.hm.dubbo.samples.api.DemoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author huwenfeng
 */
@Slf4j
@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        log.info("Dubbo Provider service is say now...");
        return "Hello " + name;
    }
}