package com.hm.feature.toogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.util.NamedFeature;

@RestController
public class MyClass {
    private FeatureManager manager;
    public static final Feature HELLO_WORLD = new NamedFeature("HELLO_WORLD");

    public MyClass(FeatureManager manager) {
        this.manager = manager;
    }

    @Autowired
    private FeatureProvider featureProvider;

    @RequestMapping("/")
    public ResponseEntity<?> index() {
        if (manager.isActive(HELLO_WORLD)) {
            // ...
            System.out.println("manager ACTIVE = " + manager);
        } else {
            System.out.println("manager DIS ACTIVE = " + manager);
        }
        return null;
    }
}