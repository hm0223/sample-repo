package com.hm.junit5.samples;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 场景: 自定义测试方法的执行顺序
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Junit5OrderedTest {
    @Test
    @Order(1)
    void nullValues() { }

    @Test
    @Order(2)
    void emptyValues() { }

    @Test
    @Order(3)
    void validValues() { }
}