// package com.hm.dubbo.samples.common.mq;
//
// import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class ProducerClient {
//
//     @Autowired
//     private RocketMQConfig mqConfig;
//     @Autowired
//     private LocalTransactionChecker localTransactionChecker;
//
//     @Bean(initMethod = "start", destroyMethod = "shutdown")
//     public ProducerTrace buildProducer() {
//         ProducerTrace producer = new ProducerTrace();
//         producer.setProperties(mqConfig.getMqProperties());
//         return producer;
//     }
// }