# 项目版本管理规范

## 版本管理策略

本项目采用统一的版本管理策略，所有模块版本由根pom.xml统一管理。

### 版本格式

版本号格式：`${revision}${changelist}`
- `revision`: 主版本号（如：1.0.0）
- `changelist`: 版本后缀（如：-SNAPSHOT）

### 版本号规则

- **开发版本**: 1.0.0-SNAPSHOT
- **预发布版本**: 1.0.0-RC1, 1.0.0-RC2
- **正式发布版本**: 1.0.0, 1.1.0
- **补丁版本**: 1.0.1, 1.0.2

## 版本升级操作指南

### 1. 开发版本升级

修改根pom.xml中的revision属性：
```xml
<properties>
    <revision>1.1.0</revision>
    <changelist>-SNAPSHOT</changelist>
</properties>
```

### 2. 发布版本

使用Maven Release Plugin进行版本发布：

```bash
# 准备发布
mvn release:prepare -DreleaseVersion=1.0.0 -DdevelopmentVersion=1.1.0-SNAPSHOT

# 执行发布
mvn release:perform
```

### 3. 手动版本更新

```bash
# 更新所有模块版本
mvn versions:set -DnewVersion=1.1.0-SNAPSHOT

# 验证版本
mvn validate
```

### 4. 版本回退

```bash
# 回退版本
mvn versions:revert
```

## 模块版本继承

所有子模块应该使用以下配置：

```xml
<parent>
    <groupId>com.hm0223</groupId>
    <artifactId>sample-repo</artifactId>
    <version>${revision}${changelist}</version>
</parent>

<artifactId>your-module-name</artifactId>
<version>${revision}${changelist}</version>
```

## 版本检查

### 检查依赖版本

```bash
# 检查依赖版本更新
mvn versions:display-dependency-updates

# 检查插件版本更新
mvn versions:display-plugin-updates

# 检查父POM版本更新
mvn versions:display-parent-updates
```

### 版本一致性验证

```bash
# 验证所有模块版本一致性
mvn validate -Drevision=1.0.0 -Dchangelist=-SNAPSHOT
```

## 持续集成版本管理

在CI/CD环境中，可以使用以下方式：

```bash
# 使用环境变量设置版本
export REVISION=1.0.0
export CHANGELIST=-SNAPSHOT
mvn clean package -Drevision=$REVISION -Dchangelist=$CHANGELIST
```

## 版本标签规范

Git标签命名规范：
- 正式发布：`v1.0.0`
- 预发布：`v1.0.0-RC1`
- 补丁版本：`v1.0.1`

## 发布流程

1. **开发阶段**: 所有模块使用-SNAPSHOT版本
2. **测试阶段**: 创建RC版本（Release Candidate）
3. **发布阶段**: 发布正式版本并打标签
4. **维护阶段**: 创建补丁版本分支

## 版本变更记录

每次版本变更需在CHANGELOG.md中记录：
- 新增功能
- 修复的Bug
- 破坏性变更
- 依赖更新