package com.hm.geek.beauty.design.ch13_ch14.endpoint;

import com.hm.geek.beauty.design.ch13_ch14.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiEndpoint.
 *
 * @author huwenfeng
 */
@Slf4j
@RestController
public class ApiEndpoint {

    @GetMapping("/api/one")
    public ApiResponse<Boolean> apiOne() {
        log.info("api one already executed ...");
        return ApiResponse.success(Boolean.TRUE);
    }

}
