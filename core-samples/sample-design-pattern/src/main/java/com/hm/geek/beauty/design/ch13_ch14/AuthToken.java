package com.hm.geek.beauty.design.ch13_ch14;

import com.hm.geek.beauty.design.ch13_ch14.util.MD5s;
import lombok.Data;
import lombok.SneakyThrows;


/**
 * AuthToken.
 *
 * @author huwenfeng
 */
@Data
public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 60000;
    
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken() {
    }

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    @SneakyThrows
    public static AuthToken generate(String baseUrl, String appId, String password, long timestamp) {
        String ciphertext = String.join(",", baseUrl, appId, password, String.valueOf(timestamp));
        String token = MD5s.strToMd5(ciphertext);
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setCreateTime(timestamp);
        return authToken;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - createTime > expiredTimeInterval;
    }

    public boolean match(AuthToken authToken) {
        return token.equals(authToken.getToken());
    }
    
}
