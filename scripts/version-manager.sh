#!/bin/bash

# 项目版本管理脚本
# 用于统一管理和升级项目版本号

set -e

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 项目根目录
PROJECT_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
POM_FILE="$PROJECT_ROOT/pom.xml"

# 帮助信息
usage() {
    echo "用法: $0 [选项]"
    echo ""
    echo "选项:"
    echo "  -h, --help              显示帮助信息"
    echo "  -v, --version VERSION   设置新版本号"
    echo "  -s, --snapshot          设置为SNAPSHOT版本"
    echo "  -r, --release           设置为RELEASE版本"
    echo "  -c, --check             检查版本一致性"
    echo "  -l, --list              列出所有模块版本"
    echo "  --dry-run               试运行，显示将要修改的文件"
    echo ""
    echo "示例:"
    echo "  $0 -v 1.1.0            # 设置版本为1.1.0-SNAPSHOT"
    echo "  $0 -v 1.1.0 -r         # 设置版本为1.1.0"
    echo "  $0 -s                  # 设置为-SNAPSHOT结尾"
    echo "  $0 -c                  # 检查版本一致性"
    echo "  $0 --dry-run -v 1.2.0  # 试运行，显示将要修改的文件"
}

# 获取当前版本
get_current_version() {
    local dependencies_pom="$PROJECT_ROOT/infrastructure/sample-dependencies/pom.xml"
    if [ -f "$dependencies_pom" ]; then
        grep -o '<revision>.*</revision>' "$dependencies_pom" | sed 's/<[^>]*>//g' | head -1
    else
        echo "未找到sample-dependencies/pom.xml文件"
        exit 1
    fi
}

# 获取当前后缀
get_current_changelist() {
    local dependencies_pom="$PROJECT_ROOT/infrastructure/sample-dependencies/pom.xml"
    if [ -f "$dependencies_pom" ]; then
        grep -o '<changelist>.*</changelist>' "$dependencies_pom" | sed 's/<[^>]*>//g' | head -1
    else
        echo "-SNAPSHOT"
    fi
}

# 试运行检查
dry_run() {
    local new_version=$1
    local new_changelist=$2
    
    echo -e "${YELLOW}试运行模式 - 将要修改的文件:${NC}"
    
    # 显示将要修改的文件
    find "$PROJECT_ROOT" -name "pom.xml" -not -path "*/target/*" -not -path "*/.git/*" | while read pom_file; do
        rel_path=$(realpath --relative-to="$PROJECT_ROOT" "$pom_file" 2>/dev/null || echo "$pom_file")
        echo "  - $rel_path"
    done
    
    echo -e "${YELLOW}将要设置的版本: ${new_version}${new_changelist}${NC}"
}

# 设置版本号
set_version() {
    local new_version=$1
    local new_changelist=$2
    local is_dry_run=$3
    local dependencies_pom="$PROJECT_ROOT/infrastructure/sample-dependencies/pom.xml"
    
    if [ "$is_dry_run" = true ]; then
        dry_run "$new_version" "$new_changelist"
        return 0
    fi
    
    echo -e "${YELLOW}正在设置版本: ${new_version}${new_changelist}${NC}"
    
    # 创建备份
    backup_dir="$PROJECT_ROOT/.version_backup_$(date +%Y%m%d_%H%M%S)"
    mkdir -p "$backup_dir"
    
    # 备份所有pom.xml文件
    echo -e "${YELLOW}创建备份...${NC}"
    find "$PROJECT_ROOT" -name "pom.xml" -not -path "*/target/*" -not -path "*/.git/*" | while read pom_file; do
        rel_path=$(realpath --relative-to="$PROJECT_ROOT" "$pom_file" 2>/dev/null || echo "$pom_file")
        mkdir -p "$backup_dir/$(dirname "$rel_path")"
        cp "$pom_file" "$backup_dir/$rel_path"
    done
    
    # 更新sample-dependencies/pom.xml中的版本
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        sed -i '' "s/<revision>.*<\/revision>/<revision>${new_version}<\/revision>/" "$dependencies_pom"
        sed -i '' "s/<changelist>.*<\/changelist>/<changelist>${new_changelist}<\/changelist>/" "$dependencies_pom"
    else
        # Linux
        sed -i "s/<revision>.*<\/revision>/<revision>${new_version}<\/revision>/" "$dependencies_pom"
        sed -i "s/<changelist>.*<\/changelist>/<changelist>${new_changelist}<\/changelist>/" "$dependencies_pom"
    fi
    
    # 更新所有子项目的pom.xml中的版本引用（仅更新父版本和项目版本，避免误替换）
    echo -e "${YELLOW}正在更新所有子项目版本引用...${NC}"
    find "$PROJECT_ROOT" -name "pom.xml" -not -path "*/target/*" -not -path "*/.git/*" | while read pom_file; do
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # 更新项目版本（仅根项目）
            if grep -q "<artifactId>sample-repo</artifactId>" "$pom_file"; then
                sed -i '' "s/\(<version>\)[^<]*\(<\/version>\)/\1${new_version}${new_changelist}\2/" "$pom_file"
            fi
            # 更新父版本（跳过spring-boot-starter-parent）
            if ! grep -q "<artifactId>spring-boot-starter-parent</artifactId>" "$pom_file"; then
                sed -i '' "/<parent>/,/<\/parent>/s/\(<version>\)[^<]*\(<\/version>\)/\1${new_version}${new_changelist}\2/" "$pom_file"
            fi
        else
            # 更新项目版本（仅根项目）
            if grep -q "<artifactId>sample-repo</artifactId>" "$pom_file"; then
                sed -i "s/\(<version>\)[^<]*\(<\/version>\)/\1${new_version}${new_changelist}\2/" "$pom_file"
            fi
            # 更新父版本（跳过spring-boot-starter-parent）
            if ! grep -q "<artifactId>spring-boot-starter-parent</artifactId>" "$pom_file"; then
                sed -i "/<parent>/,/<\/parent>/s/\(<version>\)[^<]*\(<\/version>\)/\1${new_version}${new_changelist}\2/" "$pom_file"
            fi
        fi
    done
    
    echo -e "${GREEN}版本设置完成: ${new_version}${new_changelist}${NC}"
    echo -e "${YELLOW}备份已保存到: $backup_dir${NC}"
}

# 检查版本一致性
check_consistency() {
    echo -e "${YELLOW}检查版本一致性...${NC}"
    
    cd "$PROJECT_ROOT"
    mvn validate -q
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}版本一致性检查通过${NC}"
    else
        echo -e "${RED}版本一致性检查失败${NC}"
        exit 1
    fi
}

# 列出所有模块版本
list_versions() {
    echo -e "${YELLOW}当前项目版本信息:${NC}"
    echo "根项目版本: $(get_current_version)$(get_current_changelist)"
    echo ""
    echo "模块列表:"
    
    cd "$PROJECT_ROOT"
    find . -name "pom.xml" -not -path "./target/*" -not -path "./.git/*" | while read pom; do
        if [ -f "$pom" ]; then
            module_name=$(grep -o '<artifactId>.*</artifactId>' "$pom" | head -1 | sed 's/<[^>]*>//g')
            module_version=$(grep -o '<version>.*</version>' "$pom" | head -1 | sed 's/<[^>]*>//g')
            if [ "$module_name" != "sample-repo" ] && [ "$module_name" != "sample-dependencies" ]; then
                echo "  - $module_name: $module_version"
            fi
        fi
    done
}

# 主程序
main() {
    local new_version=""
    local new_changelist=""
    local action=""
    local dry_run=false
    
    while [[ $# -gt 0 ]]; do
        case $1 in
            -h|--help)
                usage
                exit 0
                ;;
            -v|--version)
                new_version="$2"
                shift 2
                ;;
            -s|--snapshot)
                new_changelist="-SNAPSHOT"
                action="snapshot"
                shift
                ;;
            -r|--release)
                new_changelist=""
                action="release"
                shift
                ;;
            -c|--check)
                check_consistency
                exit 0
                ;;
            -l|--list)
                list_versions
                exit 0
                ;;
            --dry-run)
                dry_run=true
                shift
                ;;
            *)
                echo -e "${RED}未知选项: $1${NC}"
                usage
                exit 1
                ;;
        esac
    done
    
    # 显示当前版本
    current_version=$(get_current_version)
    current_changelist=$(get_current_changelist)
    
    if [ -z "$new_version" ]; then
        new_version=$current_version
    fi
    
    if [ -z "$new_changelist" ]; then
        if [ "$action" = "release" ]; then
            new_changelist=""
        else
            new_changelist="-SNAPSHOT"
        fi
    fi
    
    if [ -n "$new_version" ]; then
        set_version "$new_version" "$new_changelist" "$dry_run"
        if [ "$dry_run" = false ]; then
            check_consistency
        fi
    fi
}

# 执行主程序
main "$@"