package com.hm.geek.beauty.design.ch13_ch14.security;

import com.alibaba.fastjson2.JSON;
import com.hm.geek.beauty.design.ch13_ch14.ApiAuthRequest;
import com.hm.geek.beauty.design.ch13_ch14.ApiResponse;
import com.hm.geek.beauty.design.ch13_ch14.auth.ApiAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SecurityInterceptor.
 * 
 * @author huwenfeng
 */
@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private ApiAuthenticator apiAuthenticator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        // client 加密的 token 值
        String token = request.getParameter("token");
        String appId = request.getParameter("appId");
        String timestamp = request.getParameter("timestamp");
        String baseUrl = request.getParameter("baseUrl");
        response.setCharacterEncoding("utf-8");
        /*
         * 跨域ajax请求，都会先发一次method为OPTIONS的预请求
         * 1、获取服务器支持的HTTP请求方法。
         * 2、用来检查服务器的性能。例如：AJAX进行跨域请求时的预检，需要向另外一个域名的资源发送一个HTTP OPTIONS请求头，用以判断实际发送的请求是否安全。
         */
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        ApiAuthRequest apiRequest = new ApiAuthRequest();
        apiRequest.setToken(token);
        apiRequest.setAppId(appId);
        if (StringUtils.hasText(timestamp)) {
            apiRequest.setTimestamp(Long.parseLong(timestamp));
        }
        apiRequest.setBaseUrl(baseUrl);
        ApiResponse<Void> auth = apiAuthenticator.auth(apiRequest);
        if (auth.isSuccess()) {
            log.info("appId {} baseUrl {} auth success", appId, baseUrl);
            return true;
        }
        log.info("appId {} baseUrl {} auth failed cause {}", appId, baseUrl, auth.getMessage());
        String json = JSON.toJSONString(ApiResponse.error(auth.getMessage()));
        response.getWriter().write(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}

