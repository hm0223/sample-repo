package com.hm.geek.beauty.design.ch13_ch14.repo;

/**
 * MemoryCredentialStorage.
 *
 * @author huwenfeng
 */
public class MemoryCredentialStorage implements CredentialStorage {

    public static final String TEST_PASSWORD = "123456#$!";
    
    @Override
    public String getPasswordByAppId(String appId) {
        return TEST_PASSWORD;
    }
}
