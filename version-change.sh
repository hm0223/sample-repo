#!/bin/bash

# 版本变更构建脚本
# 功能：统一变更项目版本并构建

set -e

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 使用说明
usage() {
    echo "用法: $0 [选项] <新版本号>"
    echo ""
    echo "选项:"
    echo "  -h, --help     显示此帮助信息"
    echo "  -s, --skip-tests  跳过测试执行"
    echo "  -v, --verbose  显示详细输出"
    echo ""
    echo "示例:"
    echo "  $0 1.0.3-SNAPSHOT"
    echo "  $0 -s 1.0.3-SNAPSHOT"
    exit 1
}

# 日志函数
log() {
    echo -e "${GREEN}[$(date '+%Y-%m-%d %H:%M:%S')] $1${NC}"
}

warn() {
    echo -e "${YELLOW}[$(date '+%Y-%m-%d %H:%M:%S')] 警告: $1${NC}"
}

error() {
    echo -e "${RED}[$(date '+%Y-%m-%d %H:%M:%S')] 错误: $1${NC}"
    exit 1
}

# 检查参数
SKIP_TESTS=false
VERBOSE=false
NEW_VERSION=""

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            usage
            ;;
        -s|--skip-tests)
            SKIP_TESTS=true
            shift
            ;;
        -v|--verbose)
            VERBOSE=true
            shift
            ;;
        -*)
            error "未知选项: $1"
            ;;
        *)
            if [[ -z "$NEW_VERSION" ]]; then
                NEW_VERSION="$1"
            else
                error "只能指定一个版本号"
            fi
            shift
            ;;
    esac
done

if [[ -z "$NEW_VERSION" ]]; then
    usage
fi

# 验证版本号格式
if [[ ! "$NEW_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+(-[a-zA-Z0-9.-]+)?$ ]]; then
    error "版本号格式无效: $NEW_VERSION"
fi

# 获取当前版本
get_current_version() {
    if [[ -f "pom.xml" ]]; then
        local version=$(grep -m1 "<version>" pom.xml | sed 's/.*<version>\([^<]*\)<\/version>.*/\1/' | head -1)
        echo "$version"
    else
        error "未找到 pom.xml 文件"
    fi
}

# 备份原始文件
backup_files() {
    log "创建备份..."
    local backup_dir=".version_backup_$(date +%Y%m%d_%H%M%S)"
    mkdir -p "$backup_dir"
    
    # 备份所有pom.xml文件
    find . -name "pom.xml" -type f | while read -r file; do
        local rel_path=$(echo "$file" | sed 's/^\.\///')
        local backup_path="$backup_dir/$rel_path"
        mkdir -p "$(dirname "$backup_path")"
        cp "$file" "$backup_path"
    done
    
    log "备份已创建: $backup_dir"
}

# 版本升级
upgrade_version() {
    local old_version="$1"
    local new_version="$2"
    
    log "版本变更: $old_version -> $new_version"
    
    # 避免双重-SNAPSHOT问题
    local clean_new_version="$new_version"
    if [[ "$new_version" == *"SNAPSHOT-SNAPSHOT" ]]; then
        clean_new_version=$(echo "$new_version" | sed 's/-SNAPSHOT-SNAPSHOT/-SNAPSHOT/g')
        warn "修正双重-SNAPSHOT: $new_version -> $clean_new_version"
    fi
    
    # 更新所有pom.xml文件
    local count=0
    while IFS= read -r -d '' file; do
        if [[ "$VERBOSE" == "true" ]]; then
            echo "更新: $file"
        fi
        
        # 使用临时文件避免sed的BSD/GNU兼容性问题
        sed "s/$old_version/$clean_new_version/g" "$file" > "$file.tmp" && mv "$file.tmp" "$file"
        ((count++))
    done < <(find . -name "pom.xml" -type f -print0)
    
    log "已更新 $count 个pom.xml文件"
}

# 验证版本一致性
validate_versions() {
    log "验证版本一致性..."
    
    local inconsistent_files=()
    while IFS= read -r -d '' file; do
        local version=$(grep -m1 "<version>" "$file" | sed 's/.*<version>\([^<]*\)<\/version>.*/\1/')
        if [[ "$version" != "$NEW_VERSION" ]]; then
            inconsistent_files+=("$file: $version")
        fi
    done < <(find . -name "pom.xml" -type f -print0)
    
    if [[ ${#inconsistent_files[@]} -gt 0 ]]; then
        error "发现版本不一致的文件:"
        printf '%s\n' "${inconsistent_files[@]}"
    fi
    
    log "版本一致性验证通过"
}

# 安装依赖
install_dependencies() {
    log "安装sample-dependencies到本地仓库..."
    
    if [[ -f "infrastructure/sample-dependencies/pom.xml" ]]; then
        (cd "infrastructure/sample-dependencies" && mvn clean install -DskipTests)
        log "sample-dependencies安装完成"
    else
        warn "未找到sample-dependencies模块，跳过依赖安装"
    fi
}

# 项目验证
validate_project() {
    log "验证项目结构..."
    
    local maven_args=""
    if [[ "$VERBOSE" == "true" ]]; then
        maven_args="-X"
    fi
    
    mvn validate $maven_args
    log "项目验证通过"
}

# 项目构建
build_project() {
    log "开始构建项目..."
    
    local maven_args="clean install"
    if [[ "$SKIP_TESTS" == "true" ]]; then
        maven_args="$maven_args -DskipTests"
        warn "跳过测试执行"
    fi
    
    if [[ "$VERBOSE" == "true" ]]; then
        maven_args="$maven_args -X"
    fi
    
    mvn $maven_args
    log "项目构建完成"
}

# 主流程
main() {
    log "开始版本变更流程..."
    
    # 获取当前版本
    local current_version=$(get_current_version)
    log "当前版本: $current_version"
    
    # 检查是否相同版本
    if [[ "$current_version" == "$NEW_VERSION" ]]; then
        warn "当前版本已经是 $NEW_VERSION"
        exit 0
    fi
    
    # 执行升级流程
    backup_files
    upgrade_version "$current_version" "$NEW_VERSION"
    validate_versions
    install_dependencies
    validate_project
    build_project
    
    log "版本变更完成: $current_version -> $NEW_VERSION"
    
    # 显示结果
    echo ""
    log "=== 变更结果 ==="
    echo "旧版本: $current_version"
    echo "新版本: $NEW_VERSION"
    echo "跳过测试: $SKIP_TESTS"
    echo "构建状态: 成功"
}

# 执行主流程
main