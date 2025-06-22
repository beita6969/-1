#!/bin/bash

# API 测试脚本

echo "========================================"
echo "Spring Cloud 微服务 API 测试"
echo "========================================"

# 定义颜色
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

# 测试函数
test_api() {
    local name=$1
    local url=$2
    
    echo -e "\n${BLUE}测试: $name${NC}"
    echo "URL: $url"
    echo "响应:"
    curl -s "$url" | python3 -m json.tool || echo "服务未响应"
    echo "----------------------------------------"
}

# 等待服务启动
echo "等待服务启动..."
sleep 5

# 1. 测试 Eureka 服务
echo -e "\n${GREEN}=== Eureka 相关服务测试 ===${NC}"

test_api "用户服务-直接访问" "http://localhost:8081/users"
test_api "产品服务-直接访问" "http://localhost:8082/products"
test_api "消费者1-用户服务" "http://localhost:8091/consumer/users"
test_api "消费者1-健康检查" "http://localhost:8091/consumer/health"
test_api "消费者2-聚合服务" "http://localhost:8092/aggregate/all-data"

# 2. 测试 Nacos 服务
echo -e "\n${GREEN}=== Nacos 相关服务测试 ===${NC}"

test_api "Nacos提供者信息" "http://localhost:8201/nacos/service-info"
test_api "Nacos消费者调用" "http://localhost:8301/nacos/consumer/provider-info"

# 3. 检查注册中心
echo -e "\n${GREEN}=== 注册中心状态 ===${NC}"

echo -e "\n${BLUE}Eureka 注册的服务:${NC}"
curl -s http://localhost:8761/eureka/apps | grep "<application>" | sed 's/<[^>]*>//g' | sed 's/^/  - /'

echo -e "\n${BLUE}Nacos 健康状态:${NC}"
curl -s http://localhost:8848/nacos/v1/console/health || echo "Nacos 控制台: http://localhost:8848/nacos"

echo -e "\n${GREEN}========================================"
echo "API 测试完成！"
echo "========================================${NC}"