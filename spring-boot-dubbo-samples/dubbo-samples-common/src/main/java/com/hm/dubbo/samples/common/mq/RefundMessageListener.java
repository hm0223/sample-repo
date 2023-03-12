package com.hm.dubbo.samples.common.mq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;

public class RefundMessageListener extends AbstractListener {

    @Override
    public Action doConsume(Message message, ConsumeContext consumeContext) {
        return null;
    }
}