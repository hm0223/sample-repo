package com.hm.dubbo.samples.common.util;

import java.util.HashMap;
import java.util.Map;
 
 
 
public class ThreadCache {
 
    private static final ThreadLocal<Map<String, Object>> LOCAL_CACHE = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>(DEFAULT_SIZE);
        }
    };
    private static final int DEFAULT_SIZE = 8;
    public static <T> T getVal(String key) {
        return (T) LOCAL_CACHE.get().get(key);
    }
    public static <T> void setVal(String key, T val) {
        LOCAL_CACHE.get().put(key, val);
    }
    public static void clear(String key) {
        LOCAL_CACHE.get().remove(key);
    }
}