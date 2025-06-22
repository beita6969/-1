#!/bin/bash

echo "=================================="
echo "Spring Cloud 服务状态检查"
echo "=================================="
echo ""

echo "1. Eureka Dashboard:"
echo "   URL: http://localhost:8761"
echo "   状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8761)"
echo ""

echo "2. Nacos Console:"
echo "   URL: http://localhost:8848/nacos"
echo "   用户名/密码: nacos/nacos"
echo "   状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8848/nacos/)"
echo ""

echo "3. 服务提供者 API:"
echo "   - 用户服务: http://localhost:8081/users"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8081/users)"
echo "   - 产品服务: http://localhost:8082/products"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8082/products)"
echo ""

echo "4. 服务消费者 API:"
echo "   - 消费者1: http://localhost:8091/consumer/users"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8091/consumer/users)"
echo "   - 消费者2: http://localhost:8092/aggregate/all-data"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8092/aggregate/all-data)"
echo ""

echo "5. Nacos 服务 API:"
echo "   - Nacos Provider: http://localhost:8201/nacos/service-info"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8201/nacos/service-info)"
echo "   - Nacos Consumer: http://localhost:8301/nacos/consumer/provider-info"
echo "     状态: $(curl -s -o /dev/null -w "%{http_code}" http://localhost:8301/nacos/consumer/provider-info)"
echo ""

echo "=================================="
echo "提示: 状态码 200 表示服务正常运行"
echo "=================================="