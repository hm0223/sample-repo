# Maven插件版本统一管理指南

本项目采用集中式管理所有Maven插件版本，避免在各个子模块中重复定义版本号。

## 📋 使用说明

### 1. 插件版本定义位置

所有插件版本统一在 `infrastructure/sample-dependencies/pom.xml` 文件中定义：

- **编译相关插件**: `maven-compiler-plugin`, `spring-boot-maven-plugin`
- **打包插件**: `maven-jar-plugin`, `maven-war-plugin`, `maven-assembly-plugin`
- **测试插件**: `maven-surefire-plugin`, `jacoco-maven-plugin`
- **代码质量**: `maven-checkstyle-plugin`, `maven-pmd-plugin`, `spotbugs-maven-plugin`
- **构建工具**: `flatten-maven-plugin`, `maven-release-plugin`, `versions-maven-plugin`
- **辅助插件**: `exec-maven-plugin`, `dockerfile-maven-plugin`, `git-commit-id-plugin`

### 2. 在子模块中使用插件

子模块无需定义插件版本，直接从父POM继承：

#### ✅ 正确用法
```xml
<build>
    <plugins>
        <!-- 无需指定版本 -->
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

#### ❌ 错误用法
```xml
<build>
    <plugins>
        <!-- 不要重复定义版本 -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.7.5</version> <!-- 错误：版本已统一管理 -->
        </plugin>
    </plugins>
</build>
```

## 🔧 常用插件配置示例

### Spring Boot 应用
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### 代码质量检查
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-checkstyle-plugin</artifactId>
        </plugin>
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
        </plugin>
        
        <plugin>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### 测试覆盖率
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 📊 插件版本列表

| 插件名称 | 版本 | 用途 |
|---------|------|------|
| spring-boot-maven-plugin | 2.7.18 | Spring Boot打包 |
| maven-compiler-plugin | 3.10.1 | Java编译 |
| maven-surefire-plugin | 2.22.2 | 单元测试 |
| jacoco-maven-plugin | 0.8.8 | 测试覆盖率 |
| maven-checkstyle-plugin | 3.2.0 | 代码规范检查 |
| maven-pmd-plugin | 3.19.0 | 静态代码分析 |
| spotbugs-maven-plugin | 4.7.2.1 | Bug检测 |
| maven-jar-plugin | 3.3.0 | JAR打包 |
| maven-war-plugin | 3.3.2 | WAR打包 |
| maven-assembly-plugin | 3.4.2 | 自定义打包 |
| maven-shade-plugin | 3.4.1 | 可执行JAR |
| maven-javadoc-plugin | 3.4.1 | Java文档 |
| flatten-maven-plugin | 1.4.1 | POM扁平化 |
| maven-release-plugin | 3.0.0-M7 | 版本发布 |
| versions-maven-plugin | 2.15.0 | 版本管理 |
| dockerfile-maven-plugin | 1.4.13 | Docker镜像 |
| git-commit-id-plugin | 5.0.0 | Git信息注入 |

## 🚀 快速开始

1. 所有子模块自动继承插件版本管理
2. 如需添加新插件，请在 `infrastructure/sample-dependencies/pom.xml` 中添加版本定义
3. 子模块直接引用插件，无需定义版本

## 🔍 验证配置

运行以下命令验证插件配置：

```bash
# 查看所有插件版本
mvn help:effective-pom | grep -A 5 -B 5 "<plugin>"

# 检查插件是否可用
mvn plugin:help
```