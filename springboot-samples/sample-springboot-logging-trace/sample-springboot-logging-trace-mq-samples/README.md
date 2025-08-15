# 日志链路追踪MQ示例

## 项目简介

本项目是日志链路追踪系统中消息队列(MQ)部分的示例模块，展示了如何在分布式环境中通过消息队列实现日志的链路追踪。该模块提供了完整的日志收集、传输和追踪的实现方案。

## 学习目标

- 理解日志链路追踪在消息队列中的实现原理
- 掌握分布式日志追踪的核心概念和技术栈
- 学习如何在消息队列中传递和保持追踪上下文
- 了解日志链路追踪的最佳实践和性能优化

## 项目结构

```
sample-springboot-logging-trace-mq-samples/
├── src/
│   ├── main/
│   │   ├── java/           # 主要业务代码
│   │   │   ├── config/     # 配置类
│   │   │   ├── consumer/   # 消息消费者
│   │   │   ├── producer/   # 消息生产者
│   │   │   └── model/      # 数据模型
│   │   └── resources/      # 配置文件
│   └── test/               # 测试代码
├── pom.xml                 # Maven配置文件
└── README.md              # 项目说明文档
```

## 快速开始

### 前置条件

- Java 8
- Maven 3.6+
- 消息队列服务（RabbitMQ/ActiveMQ/RocketMQ）
- 链路追踪系统（Zipkin/Sleuth）

### 环境准备

1. 启动消息队列服务
2. 配置链路追踪系统
3. 确保网络连通性

### 运行示例

```bash
# 编译项目
mvn clean compile

# 运行生产者示例
mvn exec:java -Dexec.mainClass="com.example.trace.mq.producer.LogProducer"

# 运行消费者示例
mvn exec:java -Dexec.mainClass="com.example.trace.mq.consumer.LogConsumer"
```

### 验证测试

1. 发送测试消息
2. 检查链路追踪数据
3. 验证日志完整性

## 核心配置说明

### 消息队列配置

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    virtual-host: /
  
  sleuth:
    messaging:
      rabbit:
        enabled: true
```

### 链路追踪配置

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411
    sender:
      type: web
  sleuth:
    sampler:
      probability: 1.0
```

## 实现原理

### 追踪上下文传递

- **消息头传递**: 通过消息头传递追踪ID
- **上下文继承**: 保持跨服务的追踪链
- **异步处理**: 支持异步消息的链路追踪

### 关键组件

- **TraceMessageProducer**: 带追踪的生产者
- **TraceMessageConsumer**: 带追踪的消费者
- **TraceContextExtractor**: 追踪上下文提取器

## 使用场景

- 微服务架构中的日志追踪
- 分布式事务的链路追踪
- 消息驱动的异步处理追踪
- 性能监控和问题定位

## 最佳实践

### 消息设计

- 包含必要的追踪元数据
- 保持消息体的简洁性
- 使用标准化的消息格式

### 性能优化

- 合理设置采样率
- 使用异步日志记录
- 避免过度追踪

### 监控建议

- 监控消息队列的延迟
- 设置告警阈值
- 定期清理追踪数据

## 常见问题

### Q: 追踪信息丢失怎么办？
A: 检查消息头是否正确传递，确保追踪组件正确配置。

### Q: 如何处理大量追踪数据？
A: 调整采样率，使用数据压缩和批量处理。

### Q: 跨语言服务如何保持追踪？
A: 使用标准的追踪协议（如OpenTracing）。

## 相关资源

- [Spring Cloud Sleuth官方文档](https://spring.io/projects/spring-cloud-sleuth)
- [Zipkin官方文档](https://zipkin.io/)
- [消息队列最佳实践](https://www.rabbitmq.com/getstarted.html)

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。

## 许可证

本项目采用MIT许可证，详见[LICENSE](../LICENSE)文件。