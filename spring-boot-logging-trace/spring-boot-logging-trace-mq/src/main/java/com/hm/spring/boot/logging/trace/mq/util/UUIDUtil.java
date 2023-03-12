package com.hm.spring.boot.logging.trace.mq.util;

import java.util.UUID;


public class UUIDUtil {
 
    public static String generateWithoutSeparator() {
        char[] array = new char[32]; int idx = 0;
        for (char c: generate().toCharArray()) {
            if (c != 45) {
                array[idx++] = c;
                continue;
            }
        }
        return new String(array);
    }
 
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}