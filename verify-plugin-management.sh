#!/bin/bash

# Maven插件版本统一管理验证脚本
# 用于验证插件配置是否正确

echo "🔍 验证Maven插件版本统一管理配置..."
echo "================================================"

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 检查主POM文件
if [ -f "infrastructure/sample-dependencies/pom.xml" ]; then
    echo -e "${GREEN}✅ 找到插件管理配置文件${NC}"
else
    echo -e "${RED}❌ 未找到插件管理配置文件${NC}"
    exit 1
fi

# 检查插件版本定义
echo ""
echo "📊 检查插件版本定义..."
grep -A 30 "<!-- Maven 插件版本 -->" infrastructure/sample-dependencies/pom.xml | grep -E "<.*\.version>" | while read line; do
    plugin_name=$(echo $line | sed 's/.*<\(.*\)\.version>.*/\1/')
    plugin_version=$(echo $line | sed 's/.*<.*>\(.*\)<\/.*>/\1/')
    echo -e "  ${GREEN}•${NC} $plugin_name: $plugin_version"
done

# 检查pluginManagement配置
echo ""
echo "🔧 检查pluginManagement配置..."
plugin_count=$(grep -c "<plugin>" infrastructure/sample-dependencies/pom.xml)
echo -e "  ${GREEN}•${NC} 已配置插件数量: $plugin_count"

# 验证子模块继承
echo ""
echo "🧩 检查子模块继承情况..."
find . -name "pom.xml" -not -path "./infrastructure/sample-dependencies/pom.xml" -not -path "./plugin-config-examples/pom.xml" | while read pom_file; do
    if grep -q "<parent>" "$pom_file" && grep -q "sample-dependencies" "$pom_file"; then
        echo -e "  ${GREEN}•${NC} $(dirname "$pom_file"): 已继承插件管理"
    fi
done

# 验证插件可用性
echo ""
echo "⚙️ 验证插件配置有效性..."
if mvn help:effective-pom -q > /dev/null 2>&1; then
    echo -e "  ${GREEN}•${NC} Maven配置有效"
    
    # 检查Spring Boot插件版本
    spring_boot_version=$(mvn help:effective-pom -q | grep -A 5 "spring-boot-maven-plugin" | grep "<version>" | head -1 | sed 's/.*<version>\(.*\)<\/version>.*/\1/')
    if [ -n "$spring_boot_version" ]; then
        echo -e "  ${GREEN}•${NC} Spring Boot插件版本: $spring_boot_version"
    fi
    
    # 检查编译器插件版本
    compiler_version=$(mvn help:effective-pom -q | grep -A 5 "maven-compiler-plugin" | grep "<version>" | head -1 | sed 's/.*<version>\(.*\)<\/version>.*/\1/')
    if [ -n "$compiler_version" ]; then
        echo -e "  ${GREEN}•${NC} Maven编译器插件版本: $compiler_version"
    fi
else
    echo -e "  ${YELLOW}⚠️  Maven配置检查失败，请确保项目可构建${NC}"
fi

echo ""
echo "✅ 验证完成！"
echo ""
echo "📖 使用说明:"
echo "  1. 所有插件版本已在 infrastructure/sample-dependencies/pom.xml 中统一管理"
echo "  2. 子模块可直接使用插件，无需指定版本"
echo "  3. 查看 PLUGIN-MANAGEMENT.md 获取详细使用指南"
echo "  4. 参考 plugin-config-examples/pom.xml 获取配置示例"