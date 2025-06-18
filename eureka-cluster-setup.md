# Eureka Cluster Setup Instructions

## 1. Configure hosts file
Add the following lines to your /etc/hosts file:
```
127.0.0.1 peer1
127.0.0.1 peer2
127.0.0.1 peer3
```

To edit hosts file on macOS:
```bash
sudo nano /etc/hosts
```

## 2. Start Eureka Cluster
Start each Eureka server instance with different profiles:

Terminal 1:
```bash
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer1
```

Terminal 2:
```bash
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer2
```

Terminal 3:
```bash
cd eureka-server-cluster
mvn spring-boot:run -Dspring-boot.run.profiles=peer3
```

## 3. Access Eureka Dashboards
- Peer1: http://localhost:8761
- Peer2: http://localhost:8762
- Peer3: http://localhost:8763

You should see each Eureka server registered with the others in the cluster.