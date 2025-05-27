package com.hm.junit5.samples;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.function.Supplier;

/**
 * Junit5TimeoutTest.
 *
 * @author huwenfeng
 */
@SuppressWarnings("all")
class Junit5TimeoutTest {

    @Timeout(2)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(3000);
    }

    @SneakyThrows
    @Test
    void testWithoutTimeout() {
        Thread.sleep(2001);
    }

    @Test
    void testCase() {
        // Test will pass
        Assertions.assertNotEquals(3, 1 + 1);

        // Test will fail
        Assertions.assertNotEquals(4, 2 + 2, "Calculator.add(2, 2) test failed");

        // Test will fail
        Supplier<String> messageSupplier = () -> "Calculator.add(2, 2) test failed";
        Assertions.assertNotEquals(4, 2 + 2, messageSupplier);
    }

}
