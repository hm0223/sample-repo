package com.hm.geek.beauty.design.ch13_ch14;

import lombok.Data;

/**
 * ApiRequest. rename to ApiAuthRequest
 *
 * @author huwenfeng
 */
@Data
public class ApiAuthRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiAuthRequest() {
    }

    public ApiAuthRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

}
