package com.hm.dubbo.samples.common.mq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.hm.dubbo.samples.common.trace.Trace;
import com.hm.dubbo.samples.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;


public abstract class AbstractListener implements MessageListener {
    public abstract Action doConsume(Message message, ConsumeContext consumeContext);

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        try {
            String traceId = message.getUserProperties(Trace.TRACE_ID);
            MDC.put(Trace.TRACE_ID, StringUtils.isNotBlank(traceId) ? traceId :
                    StringUtils.defaultIfBlank(MDC.get(Trace.TRACE_ID), UUIDUtil.generateWithoutSeparator()));

            return this.doConsume(message, consumeContext);
        } finally {
            MDC.clear();
        }
    }
}