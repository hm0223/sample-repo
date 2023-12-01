package com.hm.junit5.samples;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Nested 注解适用于需要将测试用例分组的场景
 */
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
class JUnit5NestedTest {
    @Order(1)
    @Nested
    class TestA {
        @Test
        void test1() {
            assertEquals(1, 1);
        }
        @Test
        void test2() {
            assertEquals("hello", "hello");
        }
    }
    @Order(2)
    @Nested
    class TestB {
        @Test
        void test3() {
            assertEquals(2, 1 + 1);
        }
        @Test
        void test4() {
            assertEquals(true, true);
        }
    }
}
