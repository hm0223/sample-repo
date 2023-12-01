package com.hm.junit5.samples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

/**
 * @Tag 注解在 JUnit5 中主要用于标记测试用例，以便在执行测试时可以选择性地运行特定的测试用例。它适用于以下场景：
 * 当需要根据不同的条件来选择运行哪些测试用例时，可以使用 @Tag 注解进行标记。
 * 当需要对测试用例进行分组时，可以使用 @Tag 注解进行标记。
 * 当需要对测试用例进行优先级排序时，可以使用 @Tag 注解进行标记。
 */
class Junit5TagTest {
    @Tag("fast")
    @Test
    void testFast() {
        // 这是一个快速测试用例
    }

    @Tag("slow")
    @Test
    void testSlow() {
        // 这是一个慢速测试用例
    }
}
