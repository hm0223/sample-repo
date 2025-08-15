# 📨 Spring Boot 消息队列集成示例

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![Spring AMQP](https://img.shields.io/badge/Spring%20AMQP-3.x-blue.svg)](https://spring.io/projects/spring-amqp)
[![Apache RocketMQ](https://img.shields.io/badge/Apache%20RocketMQ-5.x-orange.svg)](https://rocketmq.apache.org/)
[![ActiveMQ](https://img.shields.io/badge/Apache%20ActiveMQ-5.x-lightgrey.svg)](https://activemq.apache.org/)

## 📋 项目简介

本项目是Spring Boot集成各种消息队列的完整示例集合，涵盖了主流消息中间件的集成和使用场景。每个子模块都是一个独立的完整示例，包含生产者、消费者、配置和监控等完整功能。

## 🎯 学习目标

- ✅ 掌握Spring Boot集成各种消息队列的方法
- ✅ 理解消息队列的核心概念和应用场景
- ✅ 学习消息生产者和消费者的实现
- ✅ 掌握消息确认机制和重试策略
- ✅ 学习分布式事务消息的实现
- ✅ 理解消息顺序性和幂等性处理
- ✅ 掌握消息监控和运维管理

## 🏗️ 项目结构

```
sample-springboot-mq/
├── 📁 sample-springboot-mq-activemq/      # ActiveMQ集成示例
│   ├── 📁 src/main/java/com/hm/mq/activemq/
│   │   ├── 📁 config/                    # ActiveMQ配置
│   │   ├── 📁 producer/                  # 消息生产者
│   │   ├── 📁 consumer/                  # 消息消费者
│   │   └── 📁 model/                     # 消息模型
│   ├── 📁 src/main/resources/
│   │   ├── 📄 application.yml            # 应用配置
│   │   └── 📄 application-activemq.yml   # ActiveMQ配置
│   └── 📄 pom.xml
├── 📁 sample-springboot-mq-rocketmq/      # RocketMQ集成示例
│   ├── 📁 src/main/java/com/hm/mq/rocketmq/
│   │   ├── 📁 config/                    # RocketMQ配置
│   │   ├── 📁 producer/                  # 消息生产者
│   │   ├── 📁 consumer/                  # 消息消费者
│   │   ├── 📁 transaction/               # 事务消息
│   │   └── 📁 model/                     # 消息模型
│   ├── 📁 src/main/resources/
│   │   ├── 📄 application.yml            # 应用配置
│   │   └── 📄 application-rocketmq.yml # RocketMQ配置
│   └── 📄 pom.xml
└── 📄 pom.xml                            # 父POM配置
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **Docker**: 用于启动消息中间件

### ⚙️ 环境准备

#### 1. 使用Docker启动消息中间件

**启动ActiveMQ:**
```bash
# 启动ActiveMQ
docker run -d --name activemq \
  -p 8161:8161 \
  -p 61616:61616 \
  rmohr/activemq:latest

# 访问管理界面
# http://localhost:8161/admin
# 默认账号密码: admin/admin
```

**启动RocketMQ:**
```bash
# 启动NameServer
docker run -d --name rmqnamesrv \
  -p 9876:9876 \
  apache/rocketmq:5.1.4 sh mqnamesrv

# 启动Broker
docker run -d --name rmqbroker \
  --link rmqnamesrv:namesrv \
  -p 10911:10911 \
  -p 10912:10912 \
  -e "NAMESRV_ADDR=namesrv:9876" \
  apache/rocketmq:5.1.4 sh mqbroker

# 启动RocketMQ Console
docker run -d --name rmqconsole \
  --link rmqnamesrv:namesrv \
  -p 8080:8080 \
  -e "JAVA_OPTS=-Drocketmq.namesrv.addr=namesrv:9876" \
  styletang/rocketmq-console-ng

# 访问管理界面
# http://localhost:8080
```

### 🏃 运行示例

#### 1. 运行ActiveMQ示例

```bash
# 启动ActiveMQ示例
cd sample-springboot-mq-activemq
mvn spring-boot:run

# 测试消息发送
curl -X POST http://localhost:8081/api/messages \
  -H "Content-Type: application/json" \
  -d '{"content":"Hello ActiveMQ!"}'

# 测试队列消息
curl -X POST http://localhost:8081/api/queue \
  -H "Content-Type: application/json" \
  -d '{"type":"queue","message":"Queue message"}'

# 测试主题消息
curl -X POST http://localhost:8081/api/topic \
  -H "Content-Type: application/json" \
  -d '{"type":"topic","message":"Topic message"}'
```

#### 2. 运行RocketMQ示例

```bash
# 启动RocketMQ示例
cd sample-springboot-mq-rocketmq
mvn spring-boot:run

# 测试同步消息
curl -X POST http://localhost:8082/api/sync \
  -H "Content-Type: application/json" \
  -d '{"message":"Sync message","tags":"test"}'

# 测试异步消息
curl -X POST http://localhost:8082/api/async \
  -H "Content-Type: application/json" \
  -d '{"message":"Async message","tags":"async"}'

# 测试顺序消息
curl -X POST http://localhost:8082/api/order \
  -H "Content-Type: application/json" \
  -d '{"orderId":123,"userId":456,"product":"iPhone"}'

# 测试事务消息
curl -X POST http://localhost:8082/api/transaction \
  -H "Content-Type: application/json" \
  -d '{"userId":123,"amount":100.00,"product":"MacBook"}'
```

## 📚 ActiveMQ集成详解

### 🎯 1. 配置说明

#### application-activemq.yml
```yaml
spring:
  activemq:
    pool:
      enabled: true
      max-connections: 50
      idle-timeout: 30000
      expiry-timeout: 60000

# 连接池配置
spring:
  activemq:
    broker-url: failover:(tcp://localhost:61616)?maxReconnectDelay=5000
    close-timeout: 15000
    send-timeout: 3000:
    broker-url: tcp://localhost:61616
      user: admin

#### RocketMQ优化
```yaml
rocketmq:
  name-server: localhost:9876
  producer:
    group: springboot-producer-group
    compress-message-body-threshold: 4096  # 压缩阈值
    retry-times-when-send-async-failed: 3
    max-message-size: 4194304
    retry-another-broker-when-not-store-ok: true
  consumer:
    group: springboot-consumer-group
    consume-thread-min: 20
    consume-thread-max: 64
    consume-timeout: 15000
    max-reconsume-times: 16
    suspend-current-queue-time-millis: 1000
```

### 🎯 3. 最佳实践

#### 消息设计原则
- **幂等性**: 确保消息处理的幂等性
- **顺序性**: 明确消息是否需要顺序处理
- **可靠性**: 实现消息确认和重试机制
- **监控**: 建立完善的监控和告警体系

#### 消息体设计
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String type;
    private String source;
    private LocalDateTime timestamp;
    private T payload;
    private Map<String, Object> metadata;
    
    public static <T> StandardMessage<T> of(String type, T payload) {
        return StandardMessage.<T>builder()
            .id(UUID.randomUUID().toString())
            .type(type)
            .source("springboot-mq")
            .timestamp(LocalDateTime.now())
            .payload(payload)
            .metadata(new HashMap<>())
            .build();
    }
}
```

## 📖 相关资源

### 📚 官方文档
- [Spring AMQP Documentation](https://spring.io/projects/spring-amqp)
- [Apache RocketMQ Documentation](https://rocketmq.apache.org/docs/)
- [Apache ActiveMQ Documentation](https://activemq.apache.org/documentation.html)

### 🛠️ 工具推荐
- **RocketMQ Console**: RocketMQ管理控制台
- **ActiveMQ Web Console**: ActiveMQ管理界面
- **Prometheus + Grafana**: 消息监控和可视化

## 🤝 贡献指南

### 📋 代码规范
- 遵循Spring Boot编码规范
- 使用Lombok简化代码
- 添加必要的注释和文档
- 编写单元测试和集成测试

## 📄 许可证

本项目采用 **MIT License** 开源协议。

---

<div align="center">
  <p><strong>⭐ 如果这个项目对你有帮助，请给它一个Star! ⭐</strong></p>
</div>
    password: admin
    packages:
      trust-all: true
    pool:
      enabled: true
      max-connections: 10
      idle-timeout: 30000

# 队列和主题配置
jms:
  template:
    default-destination: default.queue
  pub-sub-domain: false # false: 队列, true: 主题
```

### 🎯 2. 消息模型

#### 消息实体
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String content;
    private LocalDateTime timestamp;
    private String sender;
    private MessageType type;
}
```

#### 消息生产者
```java
@Component
@Slf4j
public class ActiveMQProducer {
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    public void sendToQueue(String queueName, MessageDTO message) {
        log.info("Sending message to queue: {}", queueName);
        jmsTemplate.convertAndSend(queueName, message);
    }
    
    public void sendToTopic(String topicName, MessageDTO message) {
        log.info("Sending message to topic: {}", topicName);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(topicName, message);
        jmsTemplate.setPubSubDomain(false);
    }
}
```

#### 消息消费者
```java
@Component
@Slf4j
public class ActiveMQConsumer {
    
    @JmsListener(destination = "user.queue", containerFactory = "queueListenerFactory")
    public void receiveUserMessage(MessageDTO message) {
        log.info("Received user message: {}", message);
        // 处理消息
    }
    
    @JmsListener(destination = "notification.topic", containerFactory = "topicListenerFactory")
    public void receiveNotification(MessageDTO message) {
        log.info("Received notification: {}", message);
        // 处理通知
    }
}
```

## 📚 RocketMQ集成详解

### 🎯 1. 配置说明

#### application-rocketmq.yml
```yaml
rocketmq:
  name-server: localhost:9876
  producer:
    group: springboot-producer-group
    send-message-timeout: 3000
    retry-times-when-send-failed: 2
    retry-times-when-send-async-failed: 2
    max-message-size: 4194304  # 4MB
  consumer:
    group: springboot-consumer-group
    consume-message-batch-max-size: 1
    consume-timeout: 15000
    message-model: CLUSTERING  # CLUSTERING or BROADCASTING
    consume-from-where: CONSUME_FROM_LAST_OFFSET
```

### 🎯 2. 消息类型

#### 同步消息
```java
@Component
@Slf4j
public class SyncMessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    public SendResult sendSyncMessage(String topic, String message) {
        log.info("Sending sync message to topic: {}", topic);
        return rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build());
    }
}
```

#### 异步消息
```java
@Component
@Slf4j
public class AsyncMessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    public void sendAsyncMessage(String topic, String message) {
        log.info("Sending async message to topic: {}", topic);
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(message).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Async message sent successfully: {}", sendResult);
            }
            
            @Override
            public void onException(Throwable e) {
                log.error("Failed to send async message", e);
            }
        });
    }
}
```

#### 顺序消息
```java
@Component
@Slf4j
public class OrderMessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    public void sendOrderMessage(String topic, OrderMessage orderMessage) {
        log.info("Sending order message: {}", orderMessage);
        
        Message<OrderMessage> message = MessageBuilder
            .withPayload(orderMessage)
            .setHeader(RocketMQHeaders.KEYS, orderMessage.getOrderId())
            .build();
            
        rocketMQTemplate.syncSendOrderly(topic, message, orderMessage.getUserId().toString());
    }
}
```

#### 事务消息
```java
@Component
@Slf4j
public class TransactionMessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    public TransactionSendResult sendTransactionMessage(String topic, TransactionMessage message) {
        log.info("Sending transaction message: {}", message);
        
        Message<TransactionMessage> rocketMessage = MessageBuilder
            .withPayload(message)
            .setHeader(RocketMQHeaders.TRANSACTION_ID, message.getTransactionId())
            .build();
            
        return rocketMQTemplate.sendMessageInTransaction(
            "springboot-transaction-producer-group",
            topic,
            rocketMessage,
            message
        );
    }
}
```

### 🎯 3. 消息消费者

#### 普通消息消费者
```java
@Component
@RocketMQMessageListener(
    topic = "user-topic",
    consumerGroup = "user-consumer-group",
    consumeMode = ConsumeMode.CONCURRENTLY,
    messageModel = MessageModel.CLUSTERING
)
@Slf4j
public class UserMessageConsumer implements RocketMQListener<MessageExt> {
    
    @Override
    public void onMessage(MessageExt message) {
        log.info("Received user message: {}", new String(message.getBody()));
        try {
            UserMessage userMessage = JSON.parseObject(message.getBody(), UserMessage.class);
            // 处理消息
        } catch (Exception e) {
            log.error("Failed to process user message", e);
            throw new RuntimeException("Message processing failed", e);
        }
    }
}
```

#### 顺序消息消费者
```java
@Component
@RocketMQMessageListener(
    topic = "order-topic",
    consumerGroup = "order-consumer-group",
    consumeMode = ConsumeMode.ORDERLY,
    messageModel = MessageModel.CLUSTERING
)
@Slf4j
public class OrderMessageConsumer implements RocketMQListener<MessageExt> {
    
    @Override
    public void onMessage(MessageExt message) {
        log.info("Received order message: {}", new String(message.getBody()));
        // 保证同一用户的消息顺序处理
    }
}
```

#### 事务消息监听器
```java
@Component
@RocketMQTransactionListener
@Slf4j
public class TransactionMessageListener implements RocketMQLocalTransactionListener {
    
    @Autowired
    private OrderService orderService;
    
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            TransactionMessage message = (TransactionMessage) arg;
            log.info("Executing local transaction: {}", message.getTransactionId());
            
            // 执行本地事务
            boolean result = orderService.createOrder(message);
            
            return result ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            log.error("Local transaction failed", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String transactionId = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID, String.class);
        log.info("Checking local transaction: {}", transactionId);
        
        // 检查事务状态
        boolean exists = orderService.checkTransactionStatus(transactionId);
        
        return exists ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
    }
}
```

## 🧪 测试策略

### ✅ 单元测试

#### ActiveMQ测试
```java
@SpringBootTest
class ActiveMQProducerTest {
    
    @Autowired
    private ActiveMQProducer producer;
    
    @MockBean
    private JmsTemplate jmsTemplate;
    
    @Test
    void testSendMessage() {
        MessageDTO message = new MessageDTO("1", "Test message", LocalDateTime.now(), "tester");
        producer.sendToQueue("test.queue", message);
        
        verify(jmsTemplate, times(1)).convertAndSend("test.queue", message);
    }
}
```

#### RocketMQ测试
```java
@SpringBootTest
class RocketMQProducerTest {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    @Test
    void testSendSyncMessage() {
        String message = "Test message";
        SendResult result = rocketMQTemplate.syncSend("test-topic", message);
        
        assertNotNull(result);
        assertEquals(SendStatus.SEND_OK, result.getSendStatus());
    }
}
```

### ✅ 集成测试

#### 消息消费测试
```java
@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testSendMessage() throws Exception {
        String message = "{\"content\":\"Test message\",\"sender\":\"tester\"}";
        
        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(message))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }
}
```

## 📊 监控和运维

### 🎯 1. 消息监控

#### ActiveMQ监控
- **管理界面**: http://localhost:8161/admin
- **JMX监控**: 启用JMX端口进行监控
- **指标收集**: 消息数量、消费速率、积压情况

#### RocketMQ监控
- **Console界面**: http://localhost:8080
- **Prometheus集成**: 收集消息指标
- **Grafana展示**: 可视化消息监控

### 🎯 2. 性能优化

#### ActiveMQ优化
```yaml
spring:
  activemq