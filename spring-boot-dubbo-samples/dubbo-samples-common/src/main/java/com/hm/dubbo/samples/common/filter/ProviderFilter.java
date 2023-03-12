package com.hm.dubbo.samples.common.filter;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class ProviderFilter implements Filter {
    public final static String TRACE_ID = "TRACE_ID";
    public final static String MDC_UUID = "MDC_UUID";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceID = MDC.get(MDC_UUID);
        String interfaceName = invoker.getInterface().getSimpleName();
        String methodName = invocation.getMethodName();
        String REQ_SERVICE = interfaceName + "." + methodName;
        String SOURCE_IP = RpcContext.getContext().getRemoteAddressString();
        if (StringUtils.isBlank(traceID)) {
            traceID = RpcContext.getContext().getAttachment(TRACE_ID);
        }
        if (StringUtils.isBlank(traceID)) {
            traceID = UUID.randomUUID().toString().replace("-", "");
        }
        RpcContext.getContext().setAttachment(TRACE_ID, traceID);
        Object[] args = invocation.getArguments();
        long startTime = System.currentTimeMillis();
        String logReqParam = JSON.toJSONString(args);
        MDC.put(MDC_UUID, TRACE_ID);
        MDC.put(TRACE_ID, TRACE_ID);
        log.info("Provider.dubbo请求：traceID:[{}]  消费方 [{}]  接口 [{}]  报文 [{}] ",
                traceID, SOURCE_IP, REQ_SERVICE, logReqParam);
        Result result = null;
        try {
            result = invoker.invoke(invocation);
            if (result.hasException()) {
                log.error("Provider.调用dubbo服务发生异常：called by [{}] service [{}] method [{}] fail",
                        SOURCE_IP, interfaceName, methodName);
                log.error("TraceFilter occurs exception", result.getException());
            }
        } catch (Exception e) {
            log.error("Provider.dubbo异常：traceID:[{}] called by [{}] service [{}] method [{}] exception [{}] ",
                    traceID, SOURCE_IP, invoker.getInterface().getName(), invocation.getMethodName(),
                    e.getClass().getName() + e.getMessage(), e);
            throw e;
        } finally {
            log.info("Provider.dubbo返回：traceID:[{}]  消费方 [{}]  接口 [{}]  返回值 [{}]  耗时 {} 毫秒", traceID,
                    SOURCE_IP, REQ_SERVICE, JSON.toJSONString(result), System.currentTimeMillis() - startTime);
            MDC.clear();
        }
        return result;
    }
}