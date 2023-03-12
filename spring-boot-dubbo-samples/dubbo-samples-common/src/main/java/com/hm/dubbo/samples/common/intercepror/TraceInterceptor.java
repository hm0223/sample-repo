package com.hm.dubbo.samples.common.intercepror;

import com.hm.dubbo.samples.common.util.ThreadCache;
import com.hm.dubbo.samples.common.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {
    public static final String TRACE_ID = "TRACE_ID";
    public static final String START_TIMESTAMP = "START_TIMESTAMP";
    public static final String TRACE_URI = "TRACE_URI";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String MDC_UUID = "MDC_UUID";
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ThreadCache.setVal(START_TIMESTAMP, System.currentTimeMillis());
        MDC.put(TRACE_URI, StringUtils.defaultIfBlank(request.getRequestURI(), UNKNOWN));
        MDC.put(MDC_UUID, StringUtils.defaultIfBlank(request.getHeader(TRACE_ID), UUIDUtil.generateWithoutSeparator()));
        MDC.put(TRACE_ID, StringUtils.defaultIfBlank(request.getHeader(TRACE_ID), UUIDUtil.generateWithoutSeparator()));
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("processing completed cost time [{}ms]", System.currentTimeMillis() - (long) ThreadCache.getVal(START_TIMESTAMP));
        MDC.clear();
    }
}