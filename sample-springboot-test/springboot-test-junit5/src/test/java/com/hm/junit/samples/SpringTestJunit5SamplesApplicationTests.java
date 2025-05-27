package com.hm.junit.samples;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringTestJunit5SamplesApplicationTests {

    @Test
    void contextLoads() {
    }

    @BeforeAll
    static void setUpClass() {
        // setup the common resource(s) for all tests
    }

    @AfterAll
    static void tearDownClass() {
        // close the common resource(s) for all tests
    }

    @Test
    @Disabled
    void testMethod_For_Another_Action() {
        assertEquals(2, 1, "2 is not equal to 1");  //Not executed because it is disabled
    }
}
