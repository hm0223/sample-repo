package com.hm.springcustomize;

import com.hm.springcustomize.config.XxxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huwenfeng
 */ // @ConfigurationPropertiesScan({"com.hm"})
// @EnableConfigurationProperties(value = XxxProperties.class)
@SpringBootApplication
@RequestMapping
@RestController
public class SpringCustomizeApplication {

	@Resource
	private XxxProperties xxxProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCustomizeApplication.class, args);
	}


	@GetMapping("/test")
	public String say() {
		return "say test !" + xxxProperties;
	}

}
