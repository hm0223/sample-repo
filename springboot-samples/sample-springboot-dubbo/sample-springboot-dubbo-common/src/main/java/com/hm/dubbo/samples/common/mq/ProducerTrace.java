package com.hm.dubbo.samples.common.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.hm.dubbo.samples.common.trace.Trace;
import com.hm.dubbo.samples.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;



public class ProducerTrace extends ProducerBean {

    @Override
    public SendResult send(Message message) {
        try {
            String traceId = message.getUserProperties(Trace.TRACE_ID);
            MDC.put(Trace.TRACE_ID, StringUtils.isNotBlank(traceId) ? traceId :
                    StringUtils.defaultIfBlank(MDC.get(Trace.TRACE_ID), UUIDUtil.generateWithoutSeparator()));

            return super.send(message);
        } finally {
            MDC.clear();
        }
    }
}