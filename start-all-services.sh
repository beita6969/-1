#!/bin/bash

echo "Starting all Spring Cloud services..."
echo "=================================="

# Function to open a new terminal and run command
run_in_terminal() {
    osascript -e "tell app \"Terminal\" to do script \"cd '$PWD' && $1\""
}

# Start Eureka Server
echo "1. Starting Eureka Server..."
run_in_terminal "cd eureka-server && mvn spring-boot:run"
sleep 10

# Start Service Providers
echo "2. Starting Service Provider 1..."
run_in_terminal "cd service-provider-1 && mvn spring-boot:run"
sleep 5

echo "3. Starting Service Provider 2..."
run_in_terminal "cd service-provider-2 && mvn spring-boot:run"
sleep 5

# Start Service Consumers
echo "4. Starting Service Consumer 1..."
run_in_terminal "cd service-consumer-1 && mvn spring-boot:run"
sleep 5

echo "5. Starting Service Consumer 2..."
run_in_terminal "cd service-consumer-2 && mvn spring-boot:run"
sleep 5

# Start Nacos
echo "6. Starting Nacos Server..."
if [ ! -d "nacos" ]; then
    echo "Downloading Nacos..."
    curl -O https://github.com/alibaba/nacos/releases/download/2.3.0/nacos-server-2.3.0.zip
    unzip -q nacos-server-2.3.0.zip
fi
run_in_terminal "cd nacos/bin && sh startup.sh -m standalone"
sleep 15

# Start Nacos Services
echo "7. Starting Nacos Provider..."
run_in_terminal "cd nacos-provider && mvn spring-boot:run"
sleep 5

echo "8. Starting Nacos Consumer..."
run_in_terminal "cd nacos-consumer && mvn spring-boot:run"
sleep 5

echo "=================================="
echo "All services started!"
echo ""
echo "Access points:"
echo "- Eureka Dashboard: http://localhost:8761"
echo "- Nacos Console: http://localhost:8848/nacos (nacos/nacos)"
echo "- Service Provider 1: http://localhost:8081/users"
echo "- Service Provider 2: http://localhost:8082/products"
echo "- Service Consumer 1: http://localhost:8091/consumer/users"
echo "- Service Consumer 2: http://localhost:8092/aggregate/all-data"
echo "- Nacos Provider: http://localhost:8201/nacos/service-info"
echo "- Nacos Consumer: http://localhost:8301/nacos/consumer/provider-info"
echo ""
echo "Take screenshots now!"