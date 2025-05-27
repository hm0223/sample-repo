package com.hm.geek.beauty.design.ch13_ch14.auth;

import com.hm.geek.beauty.design.ch13_ch14.ApiAuthRequest;
import com.hm.geek.beauty.design.ch13_ch14.ApiResponse;
import com.hm.geek.beauty.design.ch13_ch14.AuthToken;
import com.hm.geek.beauty.design.ch13_ch14.repo.CredentialStorage;
import com.hm.geek.beauty.design.ch13_ch14.repo.MemoryCredentialStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * DefaultApiAuthenticatorImpl.
 * 
 * @author huwenfeng
 */
@Slf4j
@Component
public class ApiAuthenticator {
    private final CredentialStorage credentialStorage;

    public ApiAuthenticator() {
        this.credentialStorage = new MemoryCredentialStorage();
    }

    public ApiAuthenticator(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }
    
    public ApiResponse<Void> auth(ApiAuthRequest apiRequest) {
        // check params... 实际业务场景中不能省略

        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();
        String baseUrl = apiRequest.getBaseUrl();
        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()) {
            return ApiResponse.error("401", "Token is expired.");
        }
        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(baseUrl, appId, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            return ApiResponse.error("401", "Token verification failed.");
        }
        log.info("appId={} baseUrl={} timestamp {} defaultApiAuthenticatorImpl#auth success.", appId, baseUrl, timestamp);
        return ApiResponse.success();
    }
}