# 🏗️ Maven项目模板(Archetype)示例

[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)

## 📋 项目简介

这是一个完整的Maven Archetype示例，用于快速创建标准化的Spring Boot多模块项目。通过该模板，您可以一键生成包含最佳实践的项目结构，避免重复的配置工作，提高开发效率。

## 🎯 核心特性

- ✅ **标准化结构**: 遵循Maven最佳实践的多模块项目结构
- ✅ **预配置依赖**: 包含常用依赖和插件配置
- ✅ **代码规范**: 集成代码质量工具(Checkstyle、SpotBugs)
- ✅ **测试框架**: 预配置JUnit 5、Mockito等测试工具
- ✅ **构建优化**: 优化的Maven构建配置
- ✅ **文档模板**: 包含标准化的README和文档模板
- ✅ **Git配置**: 预配置的.gitignore和Git工作流

## 🏗️ 项目结构

```
maven-archetype-sample/
├── 📁 src/main/resources/
│   ├── 📁 archetype-resources/
│   │   ├── 📁 __rootArtifactId__-api/         # API模块
│   │   ├── 📁 __rootArtifactId__-service/      # 业务模块
│   │   ├── 📁 __rootArtifactId__-dao/          # 数据访问模块
│   │   ├── 📁 __rootArtifactId__-web/          # Web模块
│   │   └── 📄 pom.xml                          # 父POM配置
│   ├── 📄 META-INF/maven/archetype-metadata.xml # Archetype元数据
│   └── 📄 archetype.properties                  # 默认属性
├── 📁 src/test/resources/
└── 📄 pom.xml
```

## 🚀 快速开始

### 📋 前置条件

- **Maven**: 3.6.3
- **Java**: JDK 8 或更高版本
- **Git**: 用于版本控制

### ⚙️ 安装模板

#### 方法1: 本地安装
```bash
# 1. 克隆项目
git clone <repository-url>
cd sample-repo/infrastructure/sample-maven-archetype

# 2. 生成archetype
mvn archetype:create-from-project

# 3. 安装到本地仓库
cd target/generated-sources/archetype
mvn install
```

#### 方法2: 使用已发布模板
```bash
# 直接通过坐标使用（如果已发布到仓库）
mvn archetype:generate \
  -DarchetypeGroupId=com.hm \
  -DarchetypeArtifactId=sample-maven-archetype \
  -DarchetypeVersion=1.0.0
```

### 🏃 使用模板创建项目

#### 1. 命令行方式
```bash
# 生成交互式项目
mvn archetype:generate -DarchetypeCatalog=local

# 或者直接指定参数
mvn archetype:generate \
  -DarchetypeGroupId=com.hm \
  -DarchetypeArtifactId=sample-maven-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.example \
  -DartifactId=my-project \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.example.myproject
```

#### 2. IDE集成

**IntelliJ IDEA:**
1. File → New → Project → Maven → Create from archetype
2. 点击 "Add Archetype" 添加本地archetype
3. 选择archetype并填写项目信息
4. 点击创建

**Eclipse:**
1. File → New → Maven Project
2. 选择 "Create a simple project"
3. 选择archetype并配置参数

### 📦 生成项目结构

使用模板生成的项目将包含：

```
my-project/
├── 📁 my-project-api/          # API和DTO定义
│   ├── 📁 src/main/java/
│   │   └── 📁 com/example/myproject/api/
│   │       ├── 📁 dto/
│   │       ├── 📁 vo/
│   │       └── 📁 constant/
│   └── 📄 pom.xml
├── 📁 my-project-service/       # 业务逻辑
│   ├── 📁 src/main/java/
│   │   └── 📁 com/example/myproject/service/
│   └── 📄 pom.xml
├── 📁 my-project-dao/         # 数据访问层
│   ├── 📁 src/main/java/
│   │   └── 📁 com/example/myproject/dao/
│   └── 📄 pom.xml
├── 📁 my-project-web/         # Web层和启动类
│   ├── 📁 src/main/java/
│   │   └── 📁 com/example/myproject/
│   │       └── Application.java
│   └── 📄 pom.xml
└── 📄 pom.xml                 # 父POM
```

## 📚 模板配置详解

### 🎯 1. Archetype元数据

```xml
<!-- archetype-metadata.xml -->
<archetype-descriptor name="sample-maven-archetype">
    <requiredProperties>
        <requiredProperty key="groupId">
            <defaultValue>com.example</defaultValue>
        </requiredProperty>
        <requiredProperty key="artifactId">
            <defaultValue>demo</defaultValue>
        </requiredProperty>
        <requiredProperty key="version">
            <defaultValue>1.0.0-SNAPSHOT</defaultValue>
        </requiredProperty>
        <requiredProperty key="package">
            <defaultValue>com.example.demo</defaultValue>
        </requiredProperty>
    </requiredProperties>
    
    <modules>
        <module id="${rootArtifactId}-api" dir="__rootArtifactId__-api" name="API Module">
            <fileSets>
                <fileSet filtered="true" packaged="true" encoding="UTF-8">
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.java</include>
                    </includes>
                </fileSet>
            </fileSets>
        </module>
        <!-- 其他模块... -->
    </modules>
</archetype-descriptor>
```

### 🎯 2. 父POM模板

```xml
<!-- archetype-resources/pom.xml -->
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>${groupId}</groupId>
    <artifactId>${rootArtifactId}</artifactId>
    <version>${version}</version>
    <packaging>pom</packaging>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-boot.version>2.7.5</spring-boot.version>
    </properties>
    
    <modules>
        <module>${rootArtifactId}-api</module>
        <module>${rootArtifactId}-dao</module>
        <module>${rootArtifactId}-service</module>
        <module>${rootArtifactId}-web</module>
    </modules>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

## 🛠️ 自定义模板

### 🎯 1. 添加自定义模块

1. 在 `src/main/resources/archetype-resources/` 下创建新模块目录
2. 在 `archetype-metadata.xml` 中添加模块定义
3. 更新父POM中的模块列表

### 🎯 2. 添加依赖

在对应的 `pom.xml` 模板中添加：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 🎯 3. 添加配置文件

在 `src/main/resources` 下添加：
```properties
# application.yml模板
server:
  port: 8080

spring:
  application:
    name: ${rootArtifactId}
```

## 🧪 测试模板

### ✅ 验证Archetype

```bash
# 1. 安装archetype
mvn clean install

# 2. 使用模板创建测试项目
mvn archetype:generate \
  -DarchetypeGroupId=com.hm \
  -DarchetypeArtifactId=sample-maven-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.test \
  -DartifactId=test-project \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.test.project \
  -DinteractiveMode=false

# 3. 验证生成的项目
cd test-project
mvn clean compile
```

### ✅ 自动化测试

创建测试脚本 `test-archetype.sh`：
```bash
#!/bin/bash
set -e

echo "Testing archetype..."

# 生成测试项目
mvn archetype:generate \
  -DarchetypeGroupId=com.hm \
  -DarchetypeArtifactId=sample-maven-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.test \
  -DartifactId=test-project \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.test.project \
  -DinteractiveMode=false

# 验证项目结构
cd test-project
ls -la

# 验证构建
mvn clean compile test

echo "Archetype test completed successfully!"
```

## 🎓 最佳实践

### ✅ 命名规范
- **模块命名**: 使用 `项目名-模块类型` 格式
- **包命名**: 使用 `groupId.项目名.模块名` 格式
- **版本管理**: 使用语义化版本号

### ✅ 项目结构
- **清晰分层**: API、Service、DAO、Web模块职责分明
- **依赖管理**: 父POM统一管理版本
- **配置分离**: 不同环境的配置分离
- **文档完整**: 每个模块都有清晰的README

### ✅ 构建优化
- **并行构建**: 启用Maven并行构建
- **缓存优化**: 合理使用Maven缓存
- **依赖检查**: 定期检查依赖更新

## 📋 常见问题

### ❓ 如何处理空目录？
在空目录中添加 `.gitkeep` 文件占位：
```bash
touch src/main/resources/archetype-resources/__rootArtifactId__-web/src/main/resources/.gitkeep
```

### ❓ 如何排除某些文件？
在 `archetype-metadata.xml` 中配置：
```xml
<fileSet filtered="false" encoding="UTF-8">
    <directory></directory>
    <excludes>
        <exclude>**/*.iml</exclude>
        <exclude>**/.idea/**</exclude>
    </excludes>
</fileSet>
```

### ❓ 如何发布到中央仓库？
1. 申请Sonatype账号
2. 配置GPG签名
3. 使用 `mvn deploy` 发布

## 📖 相关资源

- [Maven Archetype官方文档](https://maven.apache.org/archetype/maven-archetype-plugin/)
- [Archetype创建指南](https://maven.apache.org/guides/mini/guide-creating-archetypes.html)
- [Spring Boot项目结构](https://spring.io/guides/gs/multi-module/)
- [Maven最佳实践](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request改进模板
- 添加新的模块模板
- 完善文档和示例

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../../LICENSE)文件。