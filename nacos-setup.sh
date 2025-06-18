#!/bin/bash

# Download Nacos
echo "Downloading Nacos 2.3.0..."
curl -O https://github.com/alibaba/nacos/releases/download/2.3.0/nacos-server-2.3.0.zip

# Unzip Nacos
echo "Extracting Nacos..."
unzip -q nacos-server-2.3.0.zip

# Start Nacos in standalone mode
echo "Starting Nacos in standalone mode..."
cd nacos/bin
sh startup.sh -m standalone

echo "Nacos started successfully!"
echo "Access Nacos Console at: http://localhost:8848/nacos"
echo "Default username/password: nacos/nacos"