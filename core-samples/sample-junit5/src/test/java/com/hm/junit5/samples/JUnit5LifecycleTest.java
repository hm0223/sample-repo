package com.hm.junit5.samples;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit5LifecycleTest. 生命周期
 *
 * @author huwenfeng
 */
@SuppressWarnings("all")
class JUnit5LifecycleTest {

    @BeforeAll
    static void setUpClass() {
        // set up the common resource(s) for all tests
        System.out.println("setUpClass...");
    }

    @AfterAll
    static void tearDownClass() {
        // close the common resource(s) for all tests
        System.out.println("tearDownClass...");
    }

    @BeforeEach
    void setUpMethod() {
        // set up the common resource(s) for each test
        System.out.println("setUpMethod...");
    }

    @AfterEach
    void tearDownMethod() {
        // close the common resource(s) for each test
        System.out.println("tearDownMethod...");
    }

    /**
     * 给测试方法起别名
     */
    @DisplayName("i'm new name")
    @Test
    void testMethod_For_Some_Action() {
        // boolean result =  systemUnderTest.someMethod();
        System.out.println("testMethod_For_Some_Action...");
        boolean result =  Boolean.TRUE;
        assertTrue(result);
    }

    /**
     * 禁用测试
     */
    @Test
    @Disabled("disable reason")
    void testMethod_For_Another_Action() {
        assertEquals(2, 1, "2 is not equal to 1");  // Not executed because it is disabled
    }
}
