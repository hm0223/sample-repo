# 🚀 Dubbo + Spring Boot 微服务示例

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![Apache Dubbo](https://img.shields.io/badge/Apache%20Dubbo-3.x-blue.svg)](https://dubbo.apache.org/)
[![Zookeeper](https://img.shields.io/badge/Zookeeper-3.x-lightgrey.svg)](https://zookeeper.apache.org/)

## 📋 项目简介

这是一个完整的Dubbo + Spring Boot微服务示例项目，展示了如何使用Apache Dubbo构建分布式服务架构。项目包含服务接口定义、服务提供者、服务消费者和公共模块，实现了完整的RPC调用流程。

## 🎯 学习目标

- ✅ 理解Dubbo的核心概念和架构
- ✅ 掌握Spring Boot集成Dubbo的方法
- ✅ 学习服务注册与发现机制
- ✅ 理解负载均衡和容错策略
- ✅ 掌握分布式服务配置管理
- ✅ 学习服务监控和治理

## 🏗️ 项目结构

```
sample-springboot-dubbo/
├── 📁 sample-springboot-dubbo-common/     # 公共模块
│   ├── 📁 src/main/java/com/hm/dubbo/common/
│   │   ├── 📁 config/                    # 公共配置
│   │   ├── 📁 constant/                  # 常量定义
│   │   └── 📁 util/                      # 工具类
│   └── 📄 pom.xml
├── 📁 sample-springboot-dubbo-interface/  # 服务接口
│   ├── 📁 src/main/java/com/hm/dubbo/api/
│   │   ├── 📁 dto/                       # 数据传输对象
│   │   ├── 📁 request/                   # 请求对象
│   │   ├── 📁 response/                  # 响应对象
│   │   └── 📁 service/                   # 服务接口
│   └── 📄 pom.xml
├── 📁 sample-springboot-dubbo-provider/   # 服务提供者
│   ├── 📁 src/main/java/com/hm/dubbo/provider/
│   │   ├── 📁 service/                   # 服务实现
│   │   ├── 📁 config/                    # 提供者配置
│   │   └── Application.java              # 启动类
│   ├── 📁 src/main/resources/
│   │   ├── 📄 application.yml            # 应用配置
│   │   └── 📄 logback-spring.xml         # 日志配置
│   └── 📄 pom.xml
├── 📁 sample-springboot-dubbo-consumer/   # 服务消费者
│   ├── 📁 src/main/java/com/hm/dubbo/consumer/
│   │   ├── 📁 controller/                # 控制器
│   │   ├── 📁 service/                   # 消费服务
│   │   └── Application.java              # 启动类
│   ├── 📁 src/main/resources/
│   │   ├── 📄 application.yml            # 应用配置
│   │   └── 📄 logback-spring.xml         # 日志配置
│   └── 📄 pom.xml
└── 📄 pom.xml                            # 父POM配置
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **Zookeeper**: 3.7+ (服务注册中心)
- **MySQL**: 5.7+ 或 8.0+ (可选，用于演示)

### ⚙️ 环境准备

#### 1. 启动Zookeeper

```bash
# 使用Docker启动Zookeeper
docker run -d --name zookeeper -p 2181:2181 zookeeper:3.7

# 或者本地启动
zkServer.sh start
```

#### 2. 验证Zookeeper状态

```bash
# 检查Zookeeper状态
zkCli.sh -server localhost:2181
# 在Zookeeper CLI中执行
ls /
```

### 🏃 运行示例

#### 1. 启动服务提供者

```bash
# 启动提供者
cd sample-springboot-dubbo-provider
mvn spring-boot:run

# 或使用命令行
java -jar target/sample-springboot-dubbo-provider-*.jar
```

#### 2. 启动服务消费者

```bash
# 在新终端启动消费者
cd sample-springboot-dubbo-consumer
mvn spring-boot:run

# 或使用命令行
java -jar target/sample-springboot-dubbo-consumer-*.jar
```

#### 3. 验证服务

```bash
# 测试用户服务
curl http://localhost:8081/api/users/1

# 测试订单服务
curl http://localhost:8081/api/users/1/orders

# 测试负载均衡
curl http://localhost:8081/api/users/load-test
```

## 📚 核心配置详解

### 🎯 1. 服务接口定义

#### 用户服务接口
```java
public interface UserService {
    
    /**
     * 根据ID获取用户信息
     */
    @DubboService
    UserDTO getUserById(Long userId);
    
    /**
     * 创建用户
     */
    @DubboService
    UserDTO createUser(CreateUserRequest request);
    
    /**
     * 获取用户订单列表
     */
    @DubboService
    List<OrderDTO> getUserOrders(Long userId);
    
    /**
     * 分页查询用户
     */
    @DubboService
    PageResult<UserDTO> listUsers(PageRequest request);
}
```

#### 订单服务接口
```java
public interface OrderService {
    
    /**
     * 创建订单
     */
    @DubboService
    OrderDTO createOrder(CreateOrderRequest request);
    
    /**
     * 根据ID获取订单
     */
    @DubboService
    OrderDTO getOrderById(Long orderId);
    
    /**
     * 更新订单状态
     */
    @DubboService
    boolean updateOrderStatus(Long orderId, OrderStatus status);
}
```

### 🎯 2. 服务提供者配置

#### application.yml
```yaml
server:
  port: 8080

spring:
  application:
    name: dubbo-provider
  datasource:
    url: jdbc:mysql://localhost:3306/dubbo_demo?useSSL=false&serverTimezone=UTC
    username: root
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver

dubbo:
  application:
    name: ${spring.application.name}
  registry:
    address: zookeeper://localhost:2181
    timeout: 5000
  protocol:
    name: dubbo
    port: 20880
    host: 127.0.0.1
  scan:
    base-packages: com.hm.dubbo.provider.service
  provider:
    timeout: 5000
    retries: 0
    loadbalance: roundrobin
    filter: -exception
  consumer:
    timeout: 5000
    retries: 0
    check: false
```

#### 服务实现示例
```java
@DubboService(version = "1.0.0", timeout = 5000, retries = 0)
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderService orderService;
    
    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        return UserConverter.toDTO(user);
    }
    
    @Override
    public UserDTO createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        
        user = userRepository.save(user);
        return UserConverter.toDTO(user);
    }
    
    @Override
    public List<OrderDTO> getUserOrders(Long userId) {
        return orderService.getUserOrders(userId);
    }
}
```

### 🎯 3. 服务消费者配置

#### application.yml
```yaml
server:
  port: 8081

spring:
  application:
    name: dubbo-consumer

dubbo:
  application:
    name: ${spring.application.name}
  registry:
    address: zookeeper://localhost:2181
    timeout: 5000
  consumer:
    timeout: 5000
    retries: 0
    check: false
    loadbalance: roundrobin
  scan:
    base-packages: com.hm.dubbo.consumer.service
```

#### 消费者服务
```java
@Service
public class UserFacade {
    
    @DubboReference(version = "1.0.0", timeout = 5000, retries = 0)
    private UserService userService;
    
    @DubboReference(version = "1.0.0", timeout = 5000, retries = 0)
    private OrderService orderService;
    
    public UserDetailDTO getUserDetail(Long userId) {
        UserDTO user = userService.getUserById(userId);
        List<OrderDTO> orders = orderService.getUserOrders(userId);
        
        return UserDetailDTO.builder()
            .user(user)
            .orders(orders)
            .build();
    }
}
```

### 🎯 4. 高级特性

#### 负载均衡策略
```java
// 配置负载均衡
@DubboService(loadbalance = "roundrobin")
public class UserServiceImpl implements UserService {
    // 实现...
}

// 使用权重负载均衡
@DubboService(loadbalance = "weightedroundrobin")
public class OrderServiceImpl implements OrderService {
    // 实现...
}
```

#### 集群容错
```java
// 配置容错策略
@DubboService(cluster = "failover", retries = 2)
public class UserServiceImpl implements UserService {
    // 实现...
}

// 快速失败
@DubboService(cluster = "failfast")
public class OrderServiceImpl implements OrderService {
    // 实现...
}
```

#### 服务分组
```java
// 分组服务
@DubboService(group = "user-v1", version = "1.0.0")
public class UserServiceV1Impl implements UserService {
    // 实现...
}

@DubboService(group = "user-v2", version = "2.0.0")
public class UserServiceV2Impl implements UserService {
    // 实现...
}

// 消费者指定分组
@DubboReference(group = "user-v2", version = "2.0.0")
private UserService userService;
```

## 🧪 测试策略

### ✅ 单元测试

#### 服务提供者测试
```java
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    void testGetUserById() {
        // Given
        User user = new User(1L, "testuser", "test@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        // When
        UserDTO result = userService.getUserById(1L);
        
        // Then
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }
}
```

#### 服务消费者测试
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetUserById() throws Exception {
        // Given
        UserDTO user = new UserDTO(1L, "testuser", "test@example.com");
        when(userService.getUserById(1L)).thenReturn(user);
        
        // When & Then
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.username").value("testuser"));
    }
}
```

### ✅ 集成测试

#### Dubbo集成测试
```java
@SpringBootTest(classes = {ProviderApplication.class})
@DubboComponentScan
class DubboIntegrationTest {
    
    @DubboReference(version = "1.0.0", timeout = 5000)
    private UserService userService;
    
    @Test
    void testServiceCall() {
        UserDTO user = userService.getUserById(1L);
        assertNotNull(user);
    }
}
```

## 📊 监控和治理

### 🎯 1. Dubbo Admin

#### 启动Dubbo Admin
```bash
# 使用Docker启动Dubbo Admin
docker run -d --name dubbo-admin \
  -p 8080:8080 \
  -e admin.registry.address=zookeeper://localhost:2181 \
  apache/dubbo-admin:latest

# 访问管理界面
# http://localhost:8080
# 默认账号密码: root/root
```

### 🎯 2. 服务监控

#### 集成Prometheus
```yaml
# application.yml
dubbo:
  metrics:
    enabled: true
    port: 20880
    protocol: dubbo
    prometheus:
      enabled: true
      exporter:
        enabled: true
        port: 9090
```

#### 监控指标
- **调用次数**: dubbo_consumer_requests_total
- **响应时间**: dubbo_consumer_rt_milliseconds
- **成功率**: dubbo_consumer_success_total
- **异常率**: dubbo_consumer_exception_total

### 🎯 3. 链路追踪

#### 集成Sleuth
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
```

## 🎓 最佳实践

### ✅ 推荐做法
- **版本管理**: 使用语义化版本号
- **超时配置**: 合理设置超时时间
- **重试策略**: 谨慎使用重试，避免雪崩
- **监控告警**: 建立完善的监控体系
- **文档完善**: 维护详细的API文档
- **测试覆盖**: 保证高测试覆盖率

### ❌ 注意事项
- 避免服务循环依赖
- 不要过度使用同步调用
- 注意序列化性能
- 避免大对象传输
- 谨慎处理分布式事务

## 📈 性能优化

### 🎯 1. 连接优化
```yaml
dubbo:
  protocol:
    threads: 200
    payload: 8388608  # 8MB
  provider:
    executes: 100
    actives: 50
```

### 🎯 2. 序列化优化
```yaml
dubbo:
  protocol:
    serialization: kryo
    optimizer: com.hm.dubbo.SerializationOptimizer
```

### 🎯 3. 连接池优化
```yaml
dubbo:
  provider:
    threadpool: fixed
    threads: 200
    queues: 0
  consumer:
    connections: 10
```

## 📖 相关资源

- [Apache Dubbo官方文档](https://dubbo.apache.org/)
- [Spring Boot集成Dubbo](https://dubbo.apache.org/zh/docs/quick-start/spring-boot/)
- [Dubbo Admin](https://github.com/apache/dubbo-admin)
- [微服务架构设计](https://microservices.io/)
- [RPC框架对比](https://dubbo.apache.org/zh/docs/v2.7/user/perf-test/)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request贡献代码
- 完善文档和示例
- 分享您的Dubbo实践经验

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../LICENSE)文件。