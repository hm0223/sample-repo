package com.hm.dubbo.samples.controller;

import com.hm.dubbo.samples.service.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TraceController.
 *
 * @author huwenfeng
 */
@Slf4j
@RestController
public class TraceController {

    @Resource
    private Task task;

    @GetMapping("/trace")
    public void trace() {
        log.info("TraceController is trace now...");
        
        // dubbo 调用
        task.say();
    } 
}
