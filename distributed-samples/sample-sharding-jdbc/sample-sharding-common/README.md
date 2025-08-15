# 🔄 分片JDBC公共模块

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![ShardingSphere](https://img.shields.io/badge/ShardingSphere-5.x-blue.svg)](https://shardingsphere.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)

## 📋 项目简介

`sample-sharding-common`是分片JDBC示例项目的公共模块，提供了分片数据库的核心配置、工具类和共享模型。该模块作为其他分片示例的基础依赖，包含了数据库分片、读写分离、分布式主键生成等通用功能。

## 🎯 核心功能

- ✅ **数据库分片配置**: 水平分库分表的统一配置
- ✅ **分布式主键**: 雪花算法ID生成器
- ✅ **读写分离**: 主从数据库的读写分离策略
- ✅ **数据源配置**: 多数据源连接池配置
- ✅ **分片策略**: 基于哈希、范围、时间等分片策略
- ✅ **公共实体**: 分片相关的通用实体类
- ✅ **工具类**: 分片键计算、路由工具等

## 🏗️ 项目结构

```
sample-sharding-common/
├── 📁 src/main/java/com/hm/sharding/common/
│   ├── 📁 config/           # 分片配置类
│   │   ├── ShardingDataSourceConfig.java
│   │   ├── ShardingRuleConfiguration.java
│   │   └── ReadWriteSplittingConfig.java
│   ├── 📁 entity/           # 公共实体类
│   │   ├── BaseEntity.java
│   │   ├── ShardingTable.java
│   │   └── OrderEntity.java
│   ├── 📁 generator/        # ID生成器
│   │   ├── SnowflakeIdGenerator.java
│   │   └── DistributedIdGenerator.java
│   ├── 📁 strategy/         # 分片策略
│   │   ├── HashShardingAlgorithm.java
│   │   ├── RangeShardingAlgorithm.java
│   │   └── TimeShardingAlgorithm.java
│   ├── 📁 util/            # 工具类
│   │   ├── ShardingKeyUtil.java
│   │   ├── DataSourceUtil.java
│   │   └── ShardingContext.java
│   └── 📁 constant/        # 常量定义
│       ├── ShardingConstants.java
│       └── DatabaseConstants.java
├── 📁 src/main/resources/
│   ├── 📄 application-sharding.yml  # 分片配置
│   ├── 📄 application-datasource.yml # 数据源配置
│   └── 📄 sharding-config.yaml      # 分片规则
├── 📁 src/test/java/
│   └── 📁 com/hm/sharding/common/
│       ├── 📁 config/
│       ├── 📁 generator/
│       └── 📁 strategy/
└── 📄 pom.xml
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **MySQL**: 5.7+ 或 8.0+
- **ShardingSphere**: 5.x

### ⚙️ 数据库准备

1. **创建数据库**
   ```sql
   -- 创建分片数据库
   CREATE DATABASE IF NOT EXISTS sharding_db_0;
   CREATE DATABASE IF NOT EXISTS sharding_db_1;
   CREATE DATABASE IF NOT EXISTS sharding_db_2;
   
   -- 创建读写分离数据库
   CREATE DATABASE IF NOT EXISTS master_db;
   CREATE DATABASE IF NOT EXISTS slave_db_0;
   CREATE DATABASE IF NOT EXISTS slave_db_1;
   ```

2. **创建表结构**
   ```sql
   -- 在每个分片数据库中创建表
   USE sharding_db_0;
   CREATE TABLE IF NOT EXISTS t_order_0 (
     id BIGINT PRIMARY KEY,
     user_id BIGINT NOT NULL,
     order_no VARCHAR(64) NOT NULL,
     amount DECIMAL(10,2) NOT NULL,
     status VARCHAR(20) DEFAULT 'PENDING',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     INDEX idx_user_id (user_id),
     INDEX idx_order_no (order_no)
   );
   
   CREATE TABLE IF NOT EXISTS t_order_1 (
     -- 相同结构
   );
   ```

### 🔧 配置说明

#### 1. 数据源配置

```yaml
# application-datasource.yml
spring:
  shardingsphere:
    datasource:
      names: ds0,ds1,ds2
      ds0:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/sharding_db_0?useSSL=false&serverTimezone=UTC
        username: root
        password: password
        maximum-pool-size: 20
      ds1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/sharding_db_1?useSSL=false&serverTimezone=UTC
        username: root
        password: password
        maximum-pool-size: 20
      ds2:
        # 类似配置...
```

#### 2. 分片规则配置

```yaml
# application-sharding.yml
spring:
  shardingsphere:
    rules:
      sharding:
        tables:
          t_order:
            actual-data-nodes: ds$->{0..2}.t_order_$->{0..1}
            table-strategy:
              standard:
                sharding-column: order_id
                sharding-algorithm-name: order-table-inline
            database-strategy:
              standard:
                sharding-column: user_id
                sharding-algorithm-name: database-inline
            key-generate-strategy:
              column: id
              key-generator-name: snowflake
        sharding-algorithms:
          database-inline:
            type: INLINE
            props:
              algorithm-expression: ds$->{user_id % 3}
          order-table-inline:
            type: INLINE
            props:
              algorithm-expression: t_order_$->{order_id % 2}
        key-generators:
          snowflake:
            type: SNOWFLAKE
            props:
              worker-id: 1
```

#### 3. 读写分离配置

```yaml
# application-read-write.yml
spring:
  shardingsphere:
    rules:
      readwrite-splitting:
        data-sources:
          read_write_ds:
            type: Static
            props:
              write-data-source-name: master_ds
              read-data-source-names: slave_ds_0,slave_ds_1
            load-balancer-name: round_robin
        load-balancers:
          round_robin:
            type: ROUND_ROBIN
```

## 📚 核心组件详解

### 🎯 1. 分布式ID生成器

#### 雪花算法实现
```java
@Component
public class SnowflakeIdGenerator implements DistributedIdGenerator {
    
    private final Snowflake snowflake = new Snowflake(1, 1);
    
    @Override
    public Long generateId() {
        return snowflake.nextId();
    }
    
    @Override
    public String generateIdStr() {
        return String.valueOf(generateId());
    }
}
```

#### 使用示例
```java
@Service
public class OrderService {
    
    @Autowired
    private DistributedIdGenerator idGenerator;
    
    public Order createOrder(Long userId, BigDecimal amount) {
        Order order = new Order();
        order.setId(idGenerator.generateId());
        order.setUserId(userId);
        order.setOrderNo(generateOrderNo());
        order.setAmount(amount);
        
        return orderRepository.save(order);
    }
}
```

### 🎯 2. 分片策略实现

#### 哈希分片算法
```java
public class HashShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    
    @Override
    public String doSharding(Collection<String> availableTargetNames, 
                           PreciseShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        int shardingCount = availableTargetNames.size();
        
        int index = (int) (value % shardingCount);
        return availableTargetNames.stream()
            .sorted()
            .skip(index)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No available target"));
    }
}
```

#### 范围分片算法
```java
public class RangeShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    
    @Override
    public String doSharding(Collection<String> availableTargetNames, 
                           PreciseShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        
        if (value <= 1000) {
            return "t_order_0";
        } else if (value <= 2000) {
            return "t_order_1";
        } else {
            return "t_order_2";
        }
    }
}
```

### 🎯 3. 分片工具类

#### 分片键计算
```java
public class ShardingKeyUtil {
    
    public static int calculateDatabaseIndex(Long userId, int databaseCount) {
        return (int) (userId % databaseCount);
    }
    
    public static int calculateTableIndex(Long orderId, int tableCount) {
        return (int) (orderId % tableCount);
    }
    
    public static String buildActualTableName(String logicTable, int index) {
        return logicTable + "_" + index;
    }
}
```

#### 分片上下文
```java
@Component
public class ShardingContext {
    
    private final ThreadLocal<ShardingInfo> shardingInfo = new ThreadLocal<>();
    
    public void setCurrentShardingInfo(ShardingInfo info) {
        shardingInfo.set(info);
    }
    
    public ShardingInfo getCurrentShardingInfo() {
        return shardingInfo.get();
    }
    
    public void clear() {
        shardingInfo.remove();
    }
}
```

### 🎯 4. 公共实体类

#### 基础实体
```java
@MappedSuperclass
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @Version
    private Long version;
}
```

#### 订单实体
```java
@Entity
@Table(name = "t_order")
public class OrderEntity extends BaseEntity {
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "order_no", unique = true, nullable = false)
    private String orderNo;
    
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;
    
    // getters and setters...
}
```

## 🧪 测试策略

### ✅ 单元测试

#### 分片算法测试
```java
@ExtendWith(MockitoExtension.class)
class HashShardingAlgorithmTest {
    
    private HashShardingAlgorithm algorithm;
    
    @BeforeEach
    void setUp() {
        algorithm = new HashShardingAlgorithm();
    }
    
    @Test
    void testHashSharding() {
        Collection<String> availableTargets = Arrays.asList("ds0", "ds1", "ds2");
        PreciseShardingValue<Long> shardingValue = new PreciseShardingValue<>("t_order", "user_id", 15L);
        
        String result = algorithm.doSharding(availableTargets, shardingValue);
        
        assertEquals("ds0", result); // 15 % 3 = 0
    }
}
```

#### ID生成器测试
```java
@SpringBootTest
class SnowflakeIdGeneratorTest {
    
    @Autowired
    private SnowflakeIdGenerator idGenerator;
    
    @Test
    void testGenerateUniqueIds() {
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Long id = idGenerator.generateId();
            assertTrue(ids.add(id), "ID应该是唯一的");
        }
    }
}
```

### ✅ 集成测试

#### 分片配置测试
```java
@SpringBootTest
@AutoConfigureMockMvc
class ShardingConfigurationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testShardingConfiguration() throws Exception {
        mockMvc.perform(get("/actuator/sharding"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rules.sharding.tables.t_order.actual-data-nodes").exists());
    }
}
```

## 🎓 最佳实践

### ✅ 推荐做法
- **分片键选择**: 选择高基数且均匀分布的字段作为分片键
- **分片数量**: 预估数据增长，合理设置分片数量
- **路由策略**: 保持分片策略简单且易于维护
- **监控**: 监控分片数据分布和性能
- **备份**: 定期备份所有分片数据

### ❌ 注意事项
- 避免跨分片查询
- 不要使用自增ID作为分片键
- 谨慎处理分布式事务
- 注意分片键的数据类型一致性
- 避免热点数据集中在单个分片

## 📊 性能优化

### 📈 分片优化
- **数据分布**: 确保数据均匀分布在各个分片
- **索引优化**: 在分片键上创建适当的索引
- **查询优化**: 尽可能使用分片键进行查询
- **连接池**: 合理配置连接池参数

### 📈 监控指标
- **分片数据量**: 监控各分片的数据量差异
- **查询性能**: 监控跨分片查询的响应时间
- **连接数**: 监控各数据源的连接使用情况
- **错误率**: 监控分片操作的错误率

## 📖 相关资源

- [ShardingSphere官方文档](https://shardingsphere.apache.org/document/current/en/overview/)
- [分库分表最佳实践](https://shardingsphere.apache.org/document/current/en/user-manual/shardingsphere-jdbc/)
- [MySQL分库分表设计](https://dev.mysql.com/doc/)
- [分布式ID生成算法](https://github.com/twitter-archive/snowflake)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request贡献代码
- 完善文档和示例
- 分享您的分片实践经验

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../../LICENSE)文件。