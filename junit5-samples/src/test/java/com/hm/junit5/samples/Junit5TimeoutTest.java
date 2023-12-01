package com.hm.junit5.samples;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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
    
}
