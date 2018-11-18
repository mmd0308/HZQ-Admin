#!/bin/bash

echo "mvn clean package构建"
mvn clean package

echo "构建docker镜像"

docker build -t hzq-admin:latest .