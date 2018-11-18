#!/bin/bash
cd HZQ-Admin
bash ./build.sh



echo "进入: " $PWD

echo "执行构建脚本"
cd ../HZQ-Admin-UI

bash build.sh