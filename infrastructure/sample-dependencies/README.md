# 🎯 统一依赖管理

[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](../../LICENSE)

## 📋 项目简介

本项目作为整个代码库的统一依赖管理中心，通过Maven的`dependencyManagement`和`pluginManagement`机制，确保所有子模块使用一致的依赖版本，避免版本冲突问题。

## 🎯 核心功能

- ✅ **统一版本管理**: 所有依赖版本集中管理
- ✅ **插件版本控制**: 统一Maven插件版本
- ✅ **依赖冲突解决**: 预解决常见依赖冲突
- ✅ **最佳实践集成**: 集成业界认可的依赖版本

## 📁 项目结构

```
sample-dependencies/
├── 📄 pom.xml              # 核心配置文件
├── 📄 PLUGIN-MANAGEMENT.md # 插件管理文档
└── 📄 README.md           # 本文档
```

## 🚀 快速开始

### 📋 使用方法

#### 1. 在子模块中继承

在子模块的`pom.xml`中添加：

```xml
<parent>
    <groupId>com.example</groupId>
    <artifactId>sample-dependencies</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <relativePath>../infrastructure/sample-dependencies/pom.xml</relativePath>
</parent>
```

#### 2. 使用管理的依赖

```xml
<dependencies>
    <!-- 无需指定版本号 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

#### 3. 使用管理的插件

```xml
<build>
    <plugins>
        <!-- 无需指定版本号 -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 📊 管理的依赖版本

### 🌱 Spring Boot 生态
| 依赖 | 版本 | 说明 |
|---|---|---|
| `spring-boot` | 2.7.5 | 核心框架 |
| `spring-boot-starter-web` | 2.7.5 | Web开发 |
| `spring-boot-starter-data-jpa` | 2.7.5 | JPA支持 |
| `spring-boot-starter-test` | 2.7.5 | 测试支持 |

### 🗄️ 数据存储
| 依赖 | 版本 | 说明 |
|---|---|---|
| `mysql-connector-j` | 8.2.0 | MySQL驱动 |
| `h2` | 2.2.224 | 内存数据库 |
| `redisson` | 3.24.3 | Redis客户端 |

### 🔧 工具库
| 依赖 | 版本 | 说明 |
|---|---|---|
| `lombok` | 1.18.30 | 代码简化 |
| `guava` | 32.1.3-jre | Google工具库 |
| `commons-lang3` | 3.13.0 | Apache工具库 |

## 🔧 管理的插件版本

### 🏗️ 构建插件
| 插件 | 版本 | 说明 |
|---|---|---|
| `spring-boot-maven-plugin` | 2.7.5 | Spring Boot打包 |
| `maven-compiler-plugin` | 3.11.0 | 编译插件 |
| `maven-surefire-plugin` | 3.2.2 | 测试插件 |

### 📊 代码质量
| 插件 | 版本 | 说明 |
|---|---|---|
| `maven-checkstyle-plugin` | 3.3.1 | 代码规范检查 |
| `spotbugs-maven-plugin` | 4.8.2.0 | 静态代码分析 |
| `jacoco-maven-plugin` | 0.8.11 | 代码覆盖率 |

### 📦 打包部署
| 插件 | 版本 | 说明 |
|---|---|---|
| `maven-jar-plugin` | 3.3.0 | JAR打包 |
| `maven-source-plugin` | 3.3.0 | 源码打包 |
| `maven-javadoc-plugin` | 3.6.3 | 文档打包 |

## 🎓 使用示例

### 📋 创建新模块

1. **创建模块目录结构**
   ```bash
   mkdir new-module
   cd new-module
   ```

2. **创建pom.xml**
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0">
       <modelVersion>4.0.0</modelVersion>
       
       <parent>
           <groupId>com.example</groupId>
           <artifactId>sample-dependencies</artifactId>
           <version>1.0.0-SNAPSHOT</version>
           <relativePath>../infrastructure/sample-dependencies/pom.xml</relativePath>
       </parent>
       
       <artifactId>new-module</artifactId>
       <name>New Module</name>
       <description>新模块描述</description>
       
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
       </dependencies>
   </project>
   ```

### 🔍 验证配置

使用提供的验证脚本检查配置：

```bash
# 从项目根目录运行
./verify-plugin-management.sh
```

## ⚙️ 版本更新

### 🔄 升级依赖版本

1. **修改版本属性**
   在`pom.xml`中找到对应的版本属性并更新：
   ```xml
   <properties>
       <spring-boot.version>2.7.5</spring-boot.version>
       <!-- 其他版本属性 -->
   </properties>
   ```

2. **测试兼容性**
   ```bash
   mvn clean install
   mvn test
   ```

3. **更新文档**
   更新本文档中的版本信息表

### 📋 添加新依赖

1. **在dependencyManagement中添加**
   ```xml
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>com.new</groupId>
               <artifactId>new-library</artifactId>
               <version>1.0.0</version>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```

2. **在properties中定义版本**
   ```xml
   <properties>
       <new-library.version>1.0.0</new-library.version>
   </properties>
   ```

## 📖 相关资源

- [Maven依赖管理](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [Maven插件管理](https://maven.apache.org/pom.html#Plugin_Management)
- [Spring Boot依赖管理](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html)

## 🤝 贡献指南

如果您需要：
- 添加新的依赖管理
- 更新现有依赖版本
- 改进配置结构

请创建Issue或Pull Request，我们会及时响应。

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../LICENSE)文件。