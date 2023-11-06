package com.hm.geek.beauty.design.ch13_ch14.auth;

import com.hm.geek.beauty.design.ch13_ch14.ApiAuthRequest;
import com.hm.geek.beauty.design.ch13_ch14.ApiResponse;
import com.hm.geek.beauty.design.ch13_ch14.repo.CredentialStorage;
import com.hm.geek.beauty.design.ch13_ch14.util.MD5s;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.hm.geek.beauty.design.ch13_ch14.repo.MemoryCredentialStorage.TEST_PASSWORD;
import static org.mockito.Mockito.*;

@Slf4j
class DefaultApiAuthenticatorImplTest {
    @Mock
    CredentialStorage credentialStorage;
    @InjectMocks
    ApiAuthenticator defaultApiAuthenticatorImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuth() {
        when(credentialStorage.getPasswordByAppId(anyString())).thenReturn(TEST_PASSWORD);
        String baseUrl = "/api/one";
        String appId = "testAppId";
        long timestamp = System.currentTimeMillis();
        String ciphertext = String.join(",", baseUrl, appId, TEST_PASSWORD, String.valueOf(timestamp));
        String token = MD5s.strToMd5(ciphertext);
        log.info("baseUrl={} appId={} timestamp={} generate token is {}", baseUrl, appId, timestamp, token);
        ApiResponse<Void> result = defaultApiAuthenticatorImpl.auth(new ApiAuthRequest(baseUrl, token, appId, timestamp));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ApiResponse.SUCCESS_CODE, result.getCode());
    }
}
