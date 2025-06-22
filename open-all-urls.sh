#!/bin/bash

# 在浏览器中打开所有需要截图的 URL

echo "========================================"
echo "打开所有服务 URL"
echo "========================================"

# URLs 列表
urls=(
    "http://localhost:8761"                                    # Eureka Dashboard
    "http://localhost:8081/users"                              # Users API
    "http://localhost:8082/products"                           # Products API
    "http://localhost:8091/consumer/users"                     # Consumer 1
    "http://localhost:8092/aggregate/all-data"                 # Consumer 2
    "http://localhost:8848/nacos"                              # Nacos Console
    "http://localhost:8201/nacos/service-info"                 # Nacos Provider
    "http://localhost:8301/nacos/consumer/provider-info"       # Nacos Consumer
)

# 检查并打开 URLs
echo "正在打开所有 URLs..."
for url in "${urls[@]}"; do
    echo "打开: $url"
    open "$url"
    sleep 1
done

echo ""
echo "所有 URLs 已在浏览器中打开！"
echo ""
echo "截图提示："
echo "1. 使用 Command + Shift + 4 进行区域截图"
echo "2. 使用 Command + Shift + 4 + 空格 进行窗口截图"
echo "3. 截图会自动保存到桌面"
echo ""
echo "Nacos 登录信息："
echo "用户名: nacos"
echo "密码: nacos"