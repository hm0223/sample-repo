<a name="readme-top"></a>
<div align="center">

# 🚀 Sample Repository

**一个全面的Java技术栈示例代码库**  
包含核心Java、Spring Boot、分布式系统等各个方面的最佳实践和示例代码。

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>

# 📗 Table of Contents

- [📖 关于项目](#about-project)
    - [🛠 技术栈](#tech-stack)
    - [✨ 项目特性](#key-features)
    - [📁 项目结构](#project-structure)
- [🚀 快速开始](#getting-started)
    - [📋 前置条件](#prerequisites)
    - [⚙️ 环境搭建](#setup)
    - [📦 安装依赖](#install)
    - [▶️ 运行项目](#usage)
    - [🧪 运行测试](#run-tests)
- [📚 模块说明](#modules)
- [🤝 贡献指南](#contributing)
- [📄 许可证](#license)

<!-- PROJECT DESCRIPTION -->

# 📖 关于项目 <a name="about-project"></a>

这是一个综合性的Java技术栈示例代码库，旨在为开发者提供各种常用技术和最佳实践的参考实现。项目采用模块化设计，涵盖了从核心Java特性到现代微服务架构的各个方面。

## 🛠 技术栈 <a name="tech-stack"></a>

### 核心技术
[![Java](https://img.shields.io/badge/Java-8-important.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)

### 数据存储
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-6.0+-red.svg)](https://redis.io/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4.0+-green.svg)](https://www.mongodb.com/)

### 消息队列
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.8+-orange.svg)](https://www.rabbitmq.com/)
[![RocketMQ](https://img.shields.io/badge/RocketMQ-4.8+-blue.svg)](https://rocketmq.apache.org/)
[![ActiveMQ](https://img.shields.io/badge/ActiveMQ-5.16+-blue.svg)](https://activemq.apache.org/)

### 监控&日志
[![Spring Boot Actuator](https://img.shields.io/badge/Actuator-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Logback](https://img.shields.io/badge/Logback-1.4+-yellow.svg)](http://logback.qos.ch/)
[![Log4j2](https://img.shields.io/badge/Log4j2-2.17+-green.svg)](https://logging.apache.org/log4j/2.x/)

### 测试框架
[![JUnit5](https://img.shields.io/badge/JUnit-5.x-green.svg)](https://junit.org/junit5/)
[![Mockito](https://img.shields.io/badge/Mockito-4.x-blue.svg)](https://site.mockito.org/)

## ✨ 项目特性 <a name="key-features"></a>

- **📚 模块化设计**：清晰的模块划分，每个示例都是独立的可运行项目
- **🎯 实战导向**：每个示例都来源于实际开发场景，解决真实问题
- **🛠 最佳实践**：遵循业界最佳实践，代码规范，文档完善
- **🔄 持续更新**：紧跟技术发展趋势，定期更新示例代码
- **📖 详细文档**：每个模块都有详细的README和使用说明

## 📁 项目结构 <a name="project-structure"></a>

```
sample-repo/
├── 📁 core-samples/          # 核心Java技术示例
├── 📁 springboot-samples/    # Spring Boot技术示例
├── 📁 distributed-samples/   # 分布式系统示例
├── 📁 infrastructure/        # 基础设施和工具
├── 📁 plugin-config-examples/# 插件配置示例
└── 📄 pom.xml               # 根POM配置
```

## 📚 模块说明 <a name="modules"></a>

### 🔧 Core Samples - 核心Java技术
- **[sample-design-pattern](./core-samples/sample-design-pattern/)** - 设计模式实战：接口鉴权功能的面向对象设计
- **[sample-feature-toggle](./core-samples/sample-feature-toggle/) - 特性开关实现：动态控制功能启用/禁用
- **[sample-junit5](./core-samples/sample-junit5/) - JUnit5测试框架：全面测试示例

### 🌱 Spring Boot Samples - Spring Boot技术栈
- **[sample-springboot-config](./springboot-samples/sample-springboot-config/) - 配置管理：自定义属性配置和提示
- **[sample-springboot-devtools](./springboot-samples/sample-springboot-devtools/) - 开发工具：提升开发效率
- **[sample-springboot-dubbo](./springboot-samples/sample-springboot-dubbo/) - Dubbo集成：分布式服务调用
- **[sample-springboot-logging](./springboot-samples/sample-springboot-logging/) - 日志系统：Logback和Log4j2实现
- **[sample-springboot-logging-trace](./springboot-samples/sample-springboot-logging-trace/) - 链路追踪：分布式调用链追踪
- **[sample-springboot-mq](./springboot-samples/sample-springboot-mq/) - 消息队列：ActiveMQ和RocketMQ集成
- **[sample-springboot-test](./springboot-samples/sample-springboot-test/) - 测试实践：Spring Boot测试策略

### 🏗️ Distributed Samples - 分布式系统
- **[sample-sharding-jdbc](./distributed-samples/sample-sharding-jdbc/) - 分库分表：Sharding-JDBC实战

### 🛠️ Infrastructure - 基础设施
- **[sample-dependencies](./infrastructure/sample-dependencies/) - 依赖管理：统一的依赖版本控制
- **[sample-maven-archetype](./infrastructure/sample-maven-archetype/) - Maven原型：项目模板生成

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 🤝 贡献指南 <a name="contributing"></a>

我们欢迎所有形式的贡献！

### 📝 贡献方式
- 🐛 报告Bug
- 💡 提出新功能建议
- 📖 完善文档
- 🔧 提交代码改进

### 🔄 提交规范
1. Fork本项目
2. 创建功能分支：`git checkout -b feature/AmazingFeature`
3. 提交更改：`git commit -m 'Add some AmazingFeature'`
4. 推送到分支：`git push origin feature/AmazingFeature`
5. 创建Pull Request

### 📋 代码规范
- 遵循阿里巴巴Java开发规范
- 所有代码必须通过Checkstyle检查
- 单元测试覆盖率不低于80%
- 所有公共方法必须有Javadoc

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 📄 许可证 <a name="license"></a>

本项目采用MIT许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 🚀 快速开始 <a name="getting-started"></a>

### 📋 前置条件 <a name="prerequisites"></a>

在开始之前，请确保您的开发环境满足以下要求：

- **Java**: JDK 8
  ```bash
  java -version
  ```
- **Maven**: 3.6.3
  ```bash
  mvn -version
  ```
- **IDE**: IntelliJ IDEA 或 Eclipse (推荐 IntelliJ IDEA)
- **Git**: 用于克隆项目

### ⚙️ 环境搭建 <a name="setup"></a>

1. 克隆项目到本地
   ```bash
   git clone https://github.com/your-username/sample-repo.git
   cd sample-repo
   ```

2. 验证项目结构
   ```bash
   ls -la
   ```

### 📦 安装依赖 <a name="install"></a>

1. 安装所有依赖
   ```bash
   mvn clean install
   ```

2. 验证安装结果
   ```bash
   mvn clean compile
   ```

### ▶️ 运行项目 <a name="usage"></a>

每个示例模块都是独立的，可以单独运行：

1. 进入特定模块
   ```bash
   cd springboot-samples/sample-springboot-config
   ```

2. 运行Spring Boot应用
   ```bash
   mvn spring-boot:run
   ```

3. 访问应用
   - 默认端口: `8080`
   - 健康检查: `http://localhost:8080/actuator/health`

### 🧪 运行测试 <a name="run-tests"></a>

1. 运行所有测试
   ```bash
   mvn test
   ```

2. 运行特定模块测试
   ```bash
   cd core-samples/sample-design-pattern
   mvn test
   ```

3. 生成测试报告
   ```bash
   mvn surefire-report:report
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>