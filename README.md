# Spring Cloud Microservices Experiment

This project demonstrates a complete Spring Cloud microservices architecture with both Eureka and Nacos service registries.

## Project Structure

```
springcloud-experiment/
├── eureka-server/          # Single Eureka Server (Port: 8761)
├── eureka-server-cluster/  # Eureka Server Cluster (Ports: 8761, 8762, 8763)
├── service-provider-1/     # User Service Provider (Port: 8081)
├── service-provider-2/     # Product Service Provider (Port: 8082)
├── service-consumer-1/     # Service Consumer 1 (Port: 8091)
├── service-consumer-2/     # Service Consumer 2 - Aggregator (Port: 8092)
├── nacos-provider/        # Nacos Provider Service (Port: 8201)
├── nacos-consumer/        # Nacos Consumer Service (Port: 8301)
└── pom.xml               # Parent POM

```

## Technology Stack

- Spring Boot 3.4.4
- Spring Cloud 2024.0.1
- Eureka Server
- Nacos 2.3.0
- OpenFeign
- Maven

## Prerequisites

- JDK 17+
- Maven 3.6+
- Git

## Quick Start

### 1. Clone the repository
```bash
git clone <repository-url>
cd springcloud-experiment
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Start Eureka Services

#### Option A: Single Eureka Server
```bash
cd eureka-server
mvn spring-boot:run
```
Access: http://localhost:8761

#### Option B: Eureka Cluster
First, add to /etc/hosts:
```
127.0.0.1 peer1
127.0.0.1 peer2
127.0.0.1 peer3
```

Then start each instance:
```bash
# Terminal 1
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer1

# Terminal 2
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer2

# Terminal 3
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer3
```

Access:
- Peer1: http://localhost:8761
- Peer2: http://localhost:8762
- Peer3: http://localhost:8763

### 4. Start Service Providers
```bash
# Terminal 4
cd service-provider-1
mvn spring-boot:run

# Terminal 5
cd service-provider-2
mvn spring-boot:run
```

### 5. Start Service Consumers
```bash
# Terminal 6
cd service-consumer-1
mvn spring-boot:run

# Terminal 7
cd service-consumer-2
mvn spring-boot:run
```

### 6. Start Nacos (Optional)

#### Download and start Nacos:
```bash
./nacos-setup.sh
```

Or manually:
```bash
# Download Nacos
curl -O https://github.com/alibaba/nacos/releases/download/2.3.0/nacos-server-2.3.0.zip
unzip nacos-server-2.3.0.zip
cd nacos/bin
sh startup.sh -m standalone
```

Access Nacos Console: http://localhost:8848/nacos
Default credentials: nacos/nacos

#### Start Nacos services:
```bash
# Terminal 8
cd nacos-provider
mvn spring-boot:run

# Terminal 9
cd nacos-consumer
mvn spring-boot:run
```

## API Endpoints

### Service Provider 1 (User Service)
- GET http://localhost:8081/users - Get all users
- GET http://localhost:8081/users/{id} - Get user by ID

### Service Provider 2 (Product Service)
- GET http://localhost:8082/products - Get all products
- GET http://localhost:8082/products/{id} - Get product by ID

### Service Consumer 1
- GET http://localhost:8091/consumer/users - Get all users via Feign
- GET http://localhost:8091/consumer/users/{id} - Get user by ID via Feign

### Service Consumer 2 (Aggregator)
- GET http://localhost:8092/aggregate/user-products/{userId} - Get user with all products
- GET http://localhost:8092/aggregate/all-data - Get all users and products
- GET http://localhost:8092/aggregate/product/{productId}/with-users - Get product with all users

### Nacos Provider
- GET http://localhost:8201/nacos/hello/{name} - Hello endpoint
- GET http://localhost:8201/nacos/service-info - Service information

### Nacos Consumer
- GET http://localhost:8301/nacos/consumer/hello/{name} - Call provider via Feign
- GET http://localhost:8301/nacos/consumer/provider-info - Get provider info

## Service Registry Dashboards

- Eureka Server: http://localhost:8761
- Nacos Console: http://localhost:8848/nacos (nacos/nacos)

## Testing Service Discovery

1. Start all services as described above
2. Check Eureka dashboard to see all registered services
3. Test service calls using the API endpoints
4. Stop a provider and see how consumers handle it
5. Start multiple instances of a provider to test load balancing

## Architecture Features

- **Service Registration**: Services automatically register with Eureka/Nacos
- **Service Discovery**: Consumers discover providers through registry
- **Load Balancing**: Multiple provider instances are load balanced
- **Fault Tolerance**: Services continue working when providers fail
- **Cluster Support**: Eureka cluster for high availability

## Troubleshooting

1. If services can't register with Eureka, check:
   - Eureka server is running
   - Network connectivity
   - Application configuration

2. If Nacos won't start:
   - Check if port 8848 is available
   - Ensure you have proper permissions
   - Check Java version compatibility

3. For cluster setup:
   - Ensure hosts file is properly configured
   - Check that all cluster nodes can communicate

## Author

[Your Name]

## License

This project is for educational purposes.