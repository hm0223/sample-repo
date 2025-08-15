# 🏗️ Sharding-JDBC 分库分表示例

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![ShardingSphere](https://img.shields.io/badge/ShardingSphere-5.x-blue.svg)](https://shardingsphere.apache.org/)

## 📋 项目简介

本项目演示了如何使用 **Sharding-JDBC** 实现数据库的分库分表功能。通过实际案例展示了水平分片、垂直分片、读写分离等常见场景的解决方案。

## 🎯 学习目标

- ✅ 理解Sharding-JDBC的核心概念和架构
- ✅ 掌握分库分表的配置和使用方法
- ✅ 学习分布式主键生成策略
- ✅ 了解分布式事务处理方案
- ✅ 掌握读写分离的配置

## 🏗️ 项目结构

```
sample-sharding-jdbc/
├── 📁 sample-sharding-common/         # 公共模块
├── 📁 sample-sharding-jdbc-table/     # 单库分表示例
├── 📁 sample-sharding-jdbc-database/  # 分库分表示例
└── 📁 sample-sharding-jdbc-master-slave/ # 读写分离示例
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **MySQL**: 5.7 或更高版本
- **Spring Boot**: 3.x

### 🛠️ 环境准备

1. **创建数据库**
   ```sql
   -- 创建分库示例数据库
   CREATE DATABASE IF NOT EXISTS sharding_order_0;
   CREATE DATABASE IF NOT EXISTS sharding_order_1;
   
   -- 创建读写分离示例数据库
   CREATE DATABASE IF NOT EXISTS master_db;
   CREATE DATABASE IF NOT EXISTS slave_db_0;
   CREATE DATABASE IF NOT EXISTS slave_db_1;
   ```

2. **配置数据库连接**
   在每个模块的 `application.yml` 中配置您的数据库连接信息：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/your_database?useSSL=false&serverTimezone=UTC
       username: your_username
       password: your_password
   ```

### 🏃 运行示例

1. **启动分库分表示例**
   ```bash
   cd sample-sharding-jdbc-table
   mvn spring-boot:run
   ```

2. **验证分片效果**
   ```bash
   curl -X POST http://localhost:8080/api/orders \
     -H "Content-Type: application/json" \
     -d '{"userId": 1, "amount": 100.0}'
   ```

## 📚 核心配置说明

### 🔧 分片策略配置

#### 1. 分库分表配置
```yaml
spring:
  shardingsphere:
    datasource:
      names: ds0,ds1
      ds0:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/sharding_order_0
        username: root
        password: root
      ds1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/sharding_order_1
        username: root
        password: root
    rules:
      sharding:
        tables:
          t_order:
            actual-data-nodes: ds$->{0..1}.t_order_$->{0..1}
            table-strategy:
              standard:
                sharding-column: order_id
                sharding-algorithm-name: order-table-inline
            database-strategy:
              standard:
                sharding-column: user_id
                sharding-algorithm-name: database-inline
        sharding-algorithms:
          order-table-inline:
            type: INLINE
            props:
              algorithm-expression: t_order_$->{order_id % 2}
          database-inline:
            type: INLINE
            props:
              algorithm-expression: ds$->{user_id % 2}
```

#### 2. 读写分离配置
```yaml
spring:
  shardingsphere:
    datasource:
      names: master,slave0,slave1
      master:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: jdbc:mysql://localhost:3306/master_db
        username: root
        password: root
      slave0:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: jdbc:mysql://localhost:3306/slave_db_0
        username: root
        password: root
      slave1:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: jdbc:mysql://localhost:3306/slave_db_1
        username: root
        password: root
    rules:
      readwrite-splitting:
        data-sources:
          read-write-ds:
            type: Static
            props:
              write-data-source-name: master
              read-data-source-names: slave0,slave1
```

### 🎯 分布式主键生成

```yaml
spring:
  shardingsphere:
    rules:
      sharding:
        tables:
          t_order:
            key-generate-strategy:
              column: order_id
              key-generator-name: snowflake
        key-generators:
          snowflake:
            type: SNOWFLAKE
            props:
              worker-id: 1
```

## 🔍 验证测试

### 📊 分片效果验证

1. **查看数据分布**
   ```sql
   -- 在分库中查看数据分布
   SELECT 'ds0' as db, COUNT(*) FROM sharding_order_0.t_order_0
   UNION ALL
   SELECT 'ds0' as db, COUNT(*) FROM sharding_order_0.t_order_1
   UNION ALL
   SELECT 'ds1' as db, COUNT(*) FROM sharding_order_1.t_order_0
   UNION ALL
   SELECT 'ds1' as db, COUNT(*) FROM sharding_order_1.t_order_1;
   ```

2. **测试读写分离**
   ```bash
   # 写入数据（主库）
   curl -X POST http://localhost:8080/api/users \
     -H "Content-Type: application/json" \
     -d '{"name": "张三", "email": "zhangsan@example.com"}'

   # 读取数据（从库）
   curl http://localhost:8080/api/users
   ```

## 🎓 最佳实践

### ✅ 推荐做法
- **合理选择分片键**: 选择区分度高的字段作为分片键
- **避免跨分片查询**: 尽可能通过分片键进行查询
- **使用分布式主键**: 避免分片间的ID冲突
- **监控分片数据**: 定期检查数据分布的均衡性

### ❌ 注意事项
- 避免在事务中跨库操作
- 注意分片键的数据类型一致性
- 谨慎处理分页查询
- 避免复杂的关联查询

## 📖 相关资源

- [ShardingSphere官方文档](https://shardingsphere.apache.org/document/current/cn/overview/)
- [Spring Boot集成Sharding-JDBC](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/spring-boot-starter/)
- [分库分表最佳实践](https://github.com/apache/shardingsphere/tree/master/examples)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request贡献代码
- 完善文档和示例

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../LICENSE)文件。