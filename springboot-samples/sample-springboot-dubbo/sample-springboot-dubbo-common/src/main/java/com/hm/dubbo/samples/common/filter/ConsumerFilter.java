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
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerFilter implements Filter {
    public static final String TRACE_ID = "TRACE_ID";
    public static final String MDC_UUID = "MDC_UUID";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String interfaceName = invoker.getInterface().getName();
        String methodName = invocation.getMethodName();
        String REQ_SERVICE = interfaceName + "." + methodName;
        String SOURCE_IP = RpcContext.getContext().getRemoteAddressString();
        String traceID = RpcContext.getContext().getAttachment(TRACE_ID);

        if (StringUtils.isBlank(traceID)) {
            traceID = UUID.randomUUID().toString().replace("-", "");
        }
        RpcContext.getContext().setAttachment(TRACE_ID, traceID);
        MDC.put(MDC_UUID, traceID);
        MDC.put(TRACE_ID, traceID);
        long startTime = System.currentTimeMillis();
        Result result = null;
        try {
            result = invoker.invoke(invocation);
            if (result.hasException()) {
                log.error("Consumer.调用dubbo服务发生异常：called by [{}] service [{}] method [{}] fail",
                        SOURCE_IP, interfaceName, methodName);
                log.error("Consumer.TraceFilter occurs exception", result.getException());
            }
        } catch (Exception e) {
            log.error("Consumer.dubbo异常：traceID:[{}] called by [{}] service [{}] method [{}] exception [{}] ",
                    traceID, SOURCE_IP, invoker.getInterface().getName(), invocation.getMethodName(),
                    e.getClass().getName() + e.getMessage());
        } finally {
            log.info("Consumer.dubbo返回：traceID:[{}]  消费方 [{}]  接口 [{}]  返回值 [{}]  耗时 {} 毫秒",
                    traceID, SOURCE_IP, REQ_SERVICE, JSON.toJSONString(result), System.currentTimeMillis() - startTime);
        }
        return result;
    }
}