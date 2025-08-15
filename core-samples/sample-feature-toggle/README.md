# 🎛️ 特性开关(Feature Toggle)示例

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)

## 📋 项目简介

特性开关(Feature Toggle)是一种强大的技术模式，允许开发团队在不需要重新部署的情况下，动态地启用或禁用应用程序的特定功能。本项目展示了多种特性开关的实现方式，包括配置文件、数据库、分布式配置中心等。

## 🎯 学习目标

- ✅ 理解特性开关的核心概念和应用场景
- ✅ 掌握基于配置文件的特性开关实现
- ✅ 学习基于数据库的动态特性管理
- ✅ 了解分布式环境下的特性同步
- ✅ 掌握AOP在特性开关中的应用

## 🏗️ 项目结构

```
sample-feature-toggle/
├── 📁 src/main/java/com/hm/featuretoggle/
│   ├── 📁 annotation/        # 自定义注解
│   ├── 📁 config/           # 配置类
│   ├── 📁 controller/       # Web控制器
│   ├── 📁 model/            # 数据模型
│   ├── 📁 repository/       # 数据访问层
│   ├── 📁 service/          # 业务逻辑层
│   └── 📁 strategy/         # 特性开关策略
├── 📁 src/main/resources/
│   ├── 📄 application.yml   # 应用配置
│   └── 📄 data.sql         # 初始化数据
└── 📁 src/test/            # 测试代码
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **Spring Boot**: 3.x
- **H2 Database**: 用于演示（已内嵌）

### ⚙️ 环境配置

1. **配置文件说明**
   在 `application.yml` 中配置特性开关：
   ```yaml
   # 基础特性开关
   feature:
     toggle:
       new-user-interface: true
       advanced-search: false
       beta-feature: true
       experimental-mode: false
   
   # 数据库配置
   spring:
     datasource:
       url: jdbc:h2:mem:featuredb
       driver-class-name: org.h2.Driver
       username: sa
       password: password
   ```

2. **数据库初始化**
   项目启动时会自动创建表结构并插入演示数据。

### 🏃 运行示例

1. **启动应用**
   ```bash
   mvn spring-boot:run
   ```

2. **测试特性开关**
   ```bash
   # 获取所有特性状态
   curl http://localhost:8080/api/features
   
   # 测试新界面特性
   curl http://localhost:8080/api/features/new-ui
   
   # 切换特性状态
   curl -X POST http://localhost:8080/api/features/new-ui/toggle
   
   # 动态添加新特性
   curl -X POST http://localhost:8080/api/features \
     -H "Content-Type: application/json" \
     -d '{"name":"premium-feature","enabled":true,"description":"高级功能"}'
   ```

## 📚 实现方式详解

### 🎯 1. 基于配置文件的特性开关

#### 配置类
```java
@Component
@ConfigurationProperties(prefix = "feature.toggle")
public class FeatureToggleProperties {
    private Map<String, Boolean> features = new HashMap<>();
    
    public boolean isEnabled(String featureName) {
        return features.getOrDefault(featureName, false);
    }
}
```

#### 使用示例
```java
@Service
public class FeatureService {
    @Autowired
    private FeatureToggleProperties properties;
    
    public boolean isFeatureEnabled(String featureName) {
        return properties.isEnabled(featureName);
    }
}
```

### 🗄️ 2. 基于数据库的动态特性管理

#### 数据模型
```java
@Entity
@Table(name = "feature_toggles")
public class FeatureToggle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    private boolean enabled;
    
    private String description;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
```

#### 服务层
```java
@Service
@Transactional
public class DatabaseFeatureService {
    @Autowired
    private FeatureToggleRepository repository;
    
    public boolean isEnabled(String featureName) {
        return repository.findByName(featureName)
            .map(FeatureToggle::isEnabled)
            .orElse(false);
    }
    
    public FeatureToggle toggleFeature(String featureName) {
        FeatureToggle toggle = repository.findByName(featureName)
            .orElseThrow(() -> new FeatureNotFoundException(featureName));
        
        toggle.setEnabled(!toggle.isEnabled());
        return repository.save(toggle);
    }
}
```

### 🔀 3. 基于策略模式的特性开关

#### 策略接口
```java
public interface FeatureToggleStrategy {
    boolean isEnabled(String featureName);
    void enableFeature(String featureName);
    void disableFeature(String featureName);
}
```

#### 配置文件策略实现
```java
@Component
@ConditionalOnProperty(prefix = "feature", name = "strategy", havingValue = "config", matchIfMissing = true)
public class ConfigFileStrategy implements FeatureToggleStrategy {
    @Autowired
    private FeatureToggleProperties properties;
    
    @Override
    public boolean isEnabled(String featureName) {
        return properties.isEnabled(featureName);
    }
    
    @Override
    public void enableFeature(String featureName) {
        // 配置文件策略不支持运行时修改
        throw new UnsupportedOperationException("Config file strategy does not support runtime modification");
    }
}
```

### 🎯 4. 基于AOP的特性控制

#### 自定义注解
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureCheck {
    String value(); // 特性名称
    String fallbackMethod() default ""; // 回退方法
}
```

#### AOP切面
```java
@Aspect
@Component
public class FeatureCheckAspect {
    @Autowired
    private FeatureToggleStrategy strategy;
    
    @Around("@annotation(featureCheck)")
    public Object checkFeature(ProceedingJoinPoint joinPoint, FeatureCheck featureCheck) throws Throwable {
        if (!strategy.isEnabled(featureCheck.value())) {
            if (!featureCheck.fallbackMethod().isEmpty()) {
                return invokeFallbackMethod(joinPoint, featureCheck.fallbackMethod());
            }
            throw new FeatureNotAvailableException("Feature " + featureCheck.value() + " is not enabled");
        }
        return joinPoint.proceed();
    }
}
```

## 🎓 使用场景示例

### 🎯 场景1: 新功能灰度发布
```java
@RestController
public class UserController {
    @Autowired
    private FeatureToggleService featureService;
    
    @GetMapping("/api/users")
    public List<User> getUsers() {
        if (featureService.isEnabled("new-user-interface")) {
            return newUserService.getUsers();
        } else {
            return legacyUserService.getUsers();
        }
    }
}
```

### 🎯 场景2: A/B测试
```java
@Service
public class ExperimentService {
    @FeatureCheck(value = "experiment-v2", fallbackMethod = "useV1")
    public String processData() {
        return v2ProcessingEngine.process();
    }
    
    public String useV1() {
        return v1ProcessingEngine.process();
    }
}
```

### 🎯 场景3: 性能优化开关
```java
@Component
public class PerformanceOptimizer {
    @Autowired
    private FeatureToggleService featureService;
    
    public void optimize() {
        if (featureService.isEnabled("cache-optimization")) {
            enableAdvancedCaching();
        }
        
        if (featureService.isEnabled("async-processing")) {
            enableAsyncMode();
        }
    }
}
```

## 🧪 测试策略

### ✅ 单元测试
```java
@SpringBootTest
class FeatureToggleServiceTest {
    @Autowired
    private FeatureToggleService service;
    
    @Test
    void testFeatureToggle() {
        // 测试特性开关逻辑
        assertTrue(service.isEnabled("test-feature"));
        service.toggleFeature("test-feature");
        assertFalse(service.isEnabled("test-feature"));
    }
}
```

### ✅ 集成测试
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeatureToggleIntegrationTest {
    @Test
    void testFeatureApi() {
        // 测试API接口
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/features/test-feature", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
```

## 🎓 最佳实践

### ✅ 推荐做法
- **命名规范**: 使用小写连字符命名，如`new-feature-name`
- **分组管理**: 按业务域分组管理特性
- **监控统计**: 记录特性使用情况
- **渐进式发布**: 从内部用户开始，逐步扩大范围
- **回退策略**: 为每个新特性准备回退方案

### ❌ 注意事项
- 避免过度使用特性开关
- 定期清理不再使用的开关
- 注意开关状态的持久化
- 考虑开关对性能的影响

## 📊 监控和运维

### 📈 特性使用统计
```java
@Component
public class FeatureMetrics {
    private final MeterRegistry meterRegistry;
    
    public void recordFeatureUsage(String featureName, boolean enabled) {
        meterRegistry.counter("feature.usage", 
            "feature", featureName, 
            "enabled", String.valueOf(enabled))
            .increment();
    }
}
```

### 🔄 动态配置
```yaml
# 生产环境配置示例
management:
  endpoints:
    web:
      exposure:
        include: features
  endpoint:
    features:
      enabled: true
```

## 📖 相关资源

- [Martin Fowler - Feature Toggle](https://martinfowler.com/articles/feature-toggles.html)
- [Spring Boot Configuration Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config-typesafe-configuration-properties)
- [AOP with Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request贡献代码
- 完善文档和示例

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../LICENSE)文件。