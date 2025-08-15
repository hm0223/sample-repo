# 日志链路追踪单机示例

## 项目简介

本项目是日志链路追踪系统的单机版本示例，展示了如何在单应用环境中实现完整的日志链路追踪功能。该模块提供了轻量级的日志追踪解决方案，适用于开发环境和测试环境。

## 学习目标

- 理解日志链路追踪的基本概念和工作原理
- 掌握单机环境下的日志追踪配置和使用方法
- 学习日志追踪的核心组件和配置方式
- 了解日志追踪数据的收集和分析方法

## 项目结构

```
sample-springboot-logging-trace-standalone/
├── src/
│   ├── main/
│   │   ├── java/           # 主要业务代码
│   │   │   ├── config/     # 配置类
│   │   │   ├── controller/ # 控制器
│   │   │   ├── service/    # 业务服务
│   │   │   └── aspect/     # 日志切面
│   │   └── resources/      # 配置文件
│   └── test/               # 测试代码
├── pom.xml                 # Maven配置文件
└── README.md              # 项目说明文档
```

## 快速开始

### 前置条件

- Java 8
- Maven 3.6+
- 可选：Zipkin服务器用于数据收集

### 环境准备

1. 克隆项目到本地
2. 确保Java和Maven环境已配置
3. 如需使用Zipkin，启动Zipkin服务器

### 运行示例

```bash
# 编译项目
mvn clean compile

# 运行应用
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar target/sample-springboot-logging-trace-standalone-*.jar
```

### 验证测试

1. 访问应用端点：`http://localhost:8080`
2. 查看控制台日志输出
3. 如配置了Zipkin，访问Zipkin UI查看追踪数据

## 核心配置说明

### 应用配置

```yaml
server:
  port: 8080

spring:
  application:
    name: logging-trace-standalone
  
  sleuth:
    sampler:
      probability: 1.0  # 100%采样

logging:
  level:
    org.springframework.cloud.sleuth: DEBUG
    com.example: DEBUG
```

### Zipkin配置（可选）

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411
    sender:
      type: web
    discovery-client-enabled: false
```

## 核心功能

### 1. 请求追踪

自动为每个HTTP请求生成唯一的追踪ID，并在整个请求处理过程中传递。

### 2. 日志增强

在日志中自动添加追踪信息，包括：
- Trace ID：整个请求链的唯一标识
- Span ID：当前处理单元的标识
- Parent Span ID：父级处理单元的标识

### 3. 性能监控

记录每个请求的耗时信息，包括：
- 总处理时间
- 各阶段耗时分解
- 异常处理时间

### 4. 数据收集

支持多种数据收集方式：
- 控制台输出
- 文件日志
- Zipkin服务器
- 自定义收集器

## 使用示例

### 基本使用

```java
@RestController
public class HelloController {
    
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    
    @GetMapping("/hello")
    public String hello() {
        logger.info("处理hello请求");
        return "Hello, World!";
    }
}
```

### 服务调用追踪

```java
@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public User getUser(Long id) {
        logger.info("查询用户ID: {}", id);
        // 模拟业务处理
        return new User(id, "用户" + id);
    }
}
```

## 监控和分析

### 日志查看

日志输出示例：
```
2024-01-15 10:30:45.123 [http-nio-8080-exec-1] INFO [user-service,123e4567-e89b-12d3-a456-426614174000,456e7890-e89b-12d3-a456-426614174001] - 查询用户ID: 123
```

### Zipkin界面

访问 `http://localhost:9411` 查看：
- 服务依赖图
- 请求时间线
- 错误统计
- 性能指标

## 扩展功能

### 自定义追踪

```java
@Autowired
private Tracer tracer;

public void customTrace() {
    Span newSpan = tracer.nextSpan().name("custom-operation").start();
    try (SpanInScope ws = tracer.withSpanInScope(newSpan)) {
        // 业务逻辑
        logger.info("自定义追踪操作");
    } finally {
        newSpan.end();
    }
}
```

### 标签和注解

```java
@NewSpan("database-operation")
public void databaseOperation(@SpanTag("table") String tableName) {
    // 数据库操作
}
```

## 性能优化

### 采样配置

在生产环境中建议降低采样率：
```yaml
spring:
  sleuth:
    sampler:
      probability: 0.1  # 10%采样
```

### 异步日志

使用异步日志记录器减少性能影响：
```xml
<appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
    <queueSize>512</queueSize>
    <discardingThreshold>20</discardingThreshold>
    <appender-ref ref="FILE"/>
</appender>
```

## 常见问题

### Q: 日志中没有追踪信息？
A: 确保Spring Cloud Sleuth依赖已正确添加，并检查日志格式配置。

### Q: Zipkin中没有数据显示？
A: 检查Zipkin服务器地址配置，确保网络连通。

### Q: 性能影响太大？
A: 降低采样率，使用异步日志，优化日志级别。

## 最佳实践

### 开发环境
- 100%采样率便于调试
- 控制台输出日志
- 详细日志级别

### 测试环境
- 50%采样率
- 文件日志输出
- 集成Zipkin收集

### 生产环境
- 1-10%采样率
- 异步日志记录
- 监控告警配置

## 相关资源

- [Spring Cloud Sleuth官方文档](https://spring.io/projects/spring-cloud-sleuth)
- [Zipkin官方文档](https://zipkin.io/)
- [Spring Boot日志配置](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。

## 许可证

本项目采用MIT许可证，详见[LICENSE](../LICENSE)文件。