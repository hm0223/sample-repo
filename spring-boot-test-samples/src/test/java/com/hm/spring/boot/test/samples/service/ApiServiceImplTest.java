package com.hm.spring.boot.test.samples.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class ApiServiceImplTest {
    @Mock
    ApiService2 apiService2;
    @Mock
    Logger log;
    @InjectMocks
    ApiServiceImpl apiServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApi1() {
        when(apiService2.api2()).thenReturn("api2Response");

        String result = apiServiceImpl.api1();
        Assertions.assertEquals("api1:api2Response", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme