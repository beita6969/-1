# Screenshot Guide for Spring Cloud Experiment

## Required Screenshots

### 1. Project Structure
- IntelliJ IDEA showing the Maven parent project with all modules

### 2. Eureka Server
- Eureka Dashboard (http://localhost:8761) showing all registered services
- Show the instances registered including:
  - SERVICE-PROVIDER-1
  - SERVICE-PROVIDER-2
  - SERVICE-CONSUMER-1
  - SERVICE-CONSUMER-2

### 3. Eureka Cluster
- All three Eureka cluster dashboards showing mutual registration:
  - Peer1: http://localhost:8761
  - Peer2: http://localhost:8762
  - Peer3: http://localhost:8763

### 4. Service Provider APIs
- Browser/Postman showing:
  - http://localhost:8081/users
  - http://localhost:8082/products

### 5. Service Consumer APIs
- Browser/Postman showing remote calls:
  - http://localhost:8091/consumer/users
  - http://localhost:8092/aggregate/all-data

### 6. Nacos Console
- Nacos login page (http://localhost:8848/nacos)
- Service list showing registered services:
  - nacos-provider
  - nacos-consumer
- Service details page showing instance information

### 7. Nacos Service APIs
- Browser/Postman showing:
  - http://localhost:8201/nacos/service-info
  - http://localhost:8301/nacos/consumer/provider-info

### 8. Terminal/Console Output
- Terminal showing successful startup of services
- Maven build success output

## How to Take Screenshots on macOS

1. Full screen: Press `Command + Shift + 3`
2. Selected area: Press `Command + Shift + 4`
3. Window: Press `Command + Shift + 4`, then press `Space`

## Organize Screenshots

Create a folder structure:
```
screenshots/
├── 01-project-structure.png
├── 02-eureka-dashboard.png
├── 03-eureka-cluster-peer1.png
├── 04-eureka-cluster-peer2.png
├── 05-eureka-cluster-peer3.png
├── 06-provider-1-api.png
├── 07-provider-2-api.png
├── 08-consumer-1-api.png
├── 09-consumer-2-aggregate.png
├── 10-nacos-login.png
├── 11-nacos-service-list.png
├── 12-nacos-service-detail.png
├── 13-nacos-provider-api.png
├── 14-nacos-consumer-api.png
└── 15-terminal-output.png
```