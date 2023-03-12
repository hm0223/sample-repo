package com.hm.spring.boot.test.samples.controller;

import com.hm.spring.boot.test.samples.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ApiController.
 *
 * @author huwenfeng
 */
@RestController
public class ApiController {


    @Resource
    private ApiService apiService;
    
    
    @GetMapping("/api")
    public String api() {
        apiService.api1();
        return "";
    }
    
    
}
