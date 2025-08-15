# Sharding-JDBC 分表示例

## 项目简介

本项目是Sharding-JDBC分表策略的完整示例，展示了如何使用Sharding-JDBC实现数据表的水平分片。通过实际案例演示了基于不同分片策略的数据分表实现，包括自动分片、手动分片和多数据源配置等方案。

## 学习目标

- 理解Sharding-JDBC分表的核心概念和原理
- 掌握基于不同策略的分表配置方法
- 学习分表场景下的数据操作和查询优化
- 了解分表带来的挑战和解决方案

## 项目结构

```
sample-sharding-jdbc-table/
├── src/
│   ├── main/
│   │   ├── java/           # 主要业务代码
│   │   │   ├── config/     # Sharding配置
│   │   │   ├── controller/ # 控制器
│   │   │   ├── entity/     # 实体类
│   │   │   ├── mapper/     # 数据访问层
│   │   │   └── service/    # 业务服务层
│   │   └── resources/      # 配置文件
│   │       ├── application.yml
│   │       ├── application-auto-tables-single-source.yml
│   │       ├── application-auto-tables-multi-source.yml
│   │       ├── application-manual-tables.yml
│   │       └── application-all.yml
│   └── test/               # 测试代码
├── pom.xml                 # Maven配置文件
└── README.md              # 项目说明文档
```

## 快速开始

### 前置条件

- Java 8
- Maven 3.6+
- MySQL 5.7+ 或 PostgreSQL 10+
- 已创建分表所需的数据库和表结构

### 环境准备

1. 创建数据库
```sql
CREATE DATABASE IF NOT EXISTS sharding_table_example;
USE sharding_table_example;
```

2. 创建分表
```sql
-- 用户表分表
CREATE TABLE t_user_0 (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_user_1 (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 订单表分表
CREATE TABLE t_order_202301 (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_no VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_order_202302 (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_no VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

### 运行示例

```bash
# 克隆项目
git clone [项目地址]

# 进入项目目录
cd sample-sharding-jdbc-table

# 编译项目
mvn clean compile

# 运行应用
mvn spring-boot:run

# 或者指定配置文件运行
mvn spring-boot:run -Dspring.profiles.active=auto-tables-single-source
```

### 验证测试

1. 访问API接口：`http://localhost:8080`
2. 执行数据插入测试
3. 验证数据是否正确分片
4. 测试跨分片查询

## 分片策略详解

### 1. 自动分片-单数据源

配置文件：`application-auto-tables-single-source.yml`

基于用户ID的哈希分片：
```yaml
spring:
  shardingsphere:
    rules:
      sharding:
        tables:
          t_user:
            actual-data-nodes: ds0.t_user_$->{0..1}
            table-strategy:
              standard:
                sharding-column: id
                sharding-algorithm-name: user-table-inline
        sharding-algorithms:
          user-table-inline:
            type: INLINE
            props:
              algorithm-expression: t_user_$->{id % 2}
```

### 2. 自动分片-多数据源

配置文件：`application-auto-tables-multi-source.yml`

基于用户ID的分库分表：
```yaml
spring:
  shardingsphere:
    rules:
      sharding:
        tables:
          t_user:
            actual-data-nodes: ds$->{0..1}.t_user_$->{0..1}
            database-strategy:
              standard:
                sharding-column: id
                sharding-algorithm-name: database-inline
            table-strategy:
              standard:
                sharding-column: id
                sharding-algorithm-name: user-table-inline
        sharding-algorithms:
          database-inline:
            type: INLINE
            props:
              algorithm-expression: ds$->{id % 2}
          user-table-inline:
            type: INLINE
            props:
              algorithm-expression: t_user_$->{id % 2}
```

### 3. 手动分片

配置文件：`application-manual-tables.yml`

基于时间的订单分表：
```yaml
spring:
  shardingsphere:
    rules:
      sharding:
        tables:
          t_order:
            actual-data-nodes: ds0.t_order_$->{202301..202312}
            table-strategy:
              standard:
                sharding-column: created_time
                sharding-algorithm-name: order-table-class-based
        sharding-algorithms:
          order-table-class-based:
            type: CLASS_BASED
            props:
              strategy: STANDARD
              algorithmClassName: com.example.config.OrderShardingAlgorithm
```

### 4. 综合配置

配置文件：`application-all.yml`

包含读写分离和分片的完整配置：
```yaml
spring:
  shardingsphere:
    data-sources:
      master-ds0:
        # 主库配置
      slave-ds0:
        # 从库配置
    rules:
      readwrite-splitting:
        data-sources:
          readwrite-ds:
            type: Static
            props:
              write-data-source-name: master-ds0
              read-data-source-names: slave-ds0
      sharding:
        # 分片配置
```

## 核心功能

### 1. 数据分片

- **水平分表**：将数据按规则分散到多个表
- **分片策略**：支持哈希、范围、时间等多种策略
- **分片算法**：内置算法和自定义算法

### 2. 分布式主键

使用雪花算法生成分布式唯一ID：
```yaml
spring:
  shardingsphere:
    rules:
      sharding:
        key-generators:
          snowflake:
            type: SNOWFLAKE
            props:
              worker-id: 1
```

### 3. 读写分离

支持主从读写分离配置：
```yaml
spring:
  shardingsphere:
    rules:
      readwrite-splitting:
        data-sources:
          readwrite-ds:
            type: Static
            props:
              write-data-source-name: master-ds
              read-data-source-names: slave-ds-0,slave-ds-1
```

## 使用示例

### 实体类定义

```java
@Data
@TableName("t_user")
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdTime;
}
```

### 数据访问层

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 自动路由到对应的分表
    List<User> selectByUsername(@Param("username") String username);
}
```

### 服务层

```java
@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public void saveUser(User user) {
        // 自动分片存储
        userMapper.insert(user);
    }
    
    public List<User> getUsersByUsername(String username) {
        // 自动路由查询
        return userMapper.selectByUsername(username);
    }
}
```

### 控制器层

```java
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public String save(@RequestBody User user) {
        userService.saveUser(user);
        return "success";
    }
    
    @GetMapping("/{username}")
    public List<User> getByUsername(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }
}
```

## 测试策略

### 单元测试

```java
@SpringBootTest
@ActiveProfiles("auto-tables-single-source")
class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    void testSaveAndQuery() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@example.com");
        
        userService.saveUser(user);
        
        List<User> users = userService.getUsersByUsername("test");
        assertThat(users).hasSize(1);
    }
}
```

### 集成测试

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateAndRetrieveUser() {
        User user = new User();
        user.setUsername("integration");
        user.setEmail("integration@test.com");
        
        ResponseEntity<String> response = restTemplate.postForEntity("/user", user, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

## 性能优化

### 1. 分片策略优化

- 选择合适的分片键
- 避免热点数据问题
- 考虑数据增长趋势

### 2. 查询优化

- 支持分片键查询
- 避免全表扫描
- 使用索引优化

### 3. 连接池配置

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 30000
      connection-timeout: 20000
```

## 监控和运维

### 1. 数据分布监控

定期检查数据在各分片中的分布情况：
```sql
-- 检查各分表的数据量
SELECT 't_user_0' as table_name, COUNT(*) as count FROM t_user_0
UNION ALL
SELECT 't_user_1' as table_name, COUNT(*) as count FROM t_user_1;
```

### 2. 性能监控

- 监控分片查询性能
- 检查慢查询日志
- 监控数据库连接数

### 3. 扩容方案

- 水平扩容：增加分片数量
- 垂直扩容：提升单机性能
- 数据迁移：平滑迁移方案

## 常见问题

### Q: 如何选择分片键？
A: 选择基数大、分布均匀、查询频繁的字段作为分片键。

### Q: 如何处理跨分片查询？
A: 尽量避免跨分片查询，必要时使用聚合查询或数据冗余。

### Q: 如何平滑扩容？
A: 使用在线扩容工具，或采用双写方案进行数据迁移。

### Q: 如何处理热点数据？
A: 使用一致性哈希算法，或增加缓存层。

## 相关资源

- [ShardingSphere官方文档](https://shardingsphere.apache.org/)
- [分库分表最佳实践](https://shardingsphere.apache.org/document/current/cn/features/sharding/)
- [Spring Boot集成Sharding-JDBC](https://spring.io/projects/spring-boot)

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。

## 许可证

本项目采用MIT许可证，详见[LICENSE](../LICENSE)文件。