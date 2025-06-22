#!/bin/bash

echo "打开所有服务页面用于截图..."
echo "=================================="

# Eureka Dashboard
open "http://localhost:8761"
echo "1. Eureka Dashboard: http://localhost:8761"

# Nacos Console
open "http://localhost:8848/nacos"
echo "2. Nacos Console: http://localhost:8848/nacos (用户名/密码: nacos/nacos)"

# 服务API测试
sleep 2
open "http://localhost:8081/users"
echo "3. 用户服务API: http://localhost:8081/users"

sleep 1
open "http://localhost:8082/products"
echo "4. 产品服务API: http://localhost:8082/products"

sleep 1
open "http://localhost:8091/consumer/users"
echo "5. 消费者1 API: http://localhost:8091/consumer/users"

sleep 1
open "http://localhost:8092/aggregate/all-data"
echo "6. 消费者2聚合API: http://localhost:8092/aggregate/all-data"

echo ""
echo "所有页面已打开，请按照实验报告中的标注进行截图！"