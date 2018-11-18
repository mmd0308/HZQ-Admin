#!/bin/bash
echo "启动后台管理系统docker镜像"

docker run -p 80:80 --name hzq-admin-ui --link hzq-admin:hzq-admin -itd hzq-admin-ui:latest