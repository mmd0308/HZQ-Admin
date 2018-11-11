#!/bin/bash
# 构建docker镜像
# 将前段页面和后台程序构建成一个jar包

# 进入前段项目中
cd HZQ-Admin-UI

echo "npm install 导入依赖"
# 导入依赖
npm install --registry=https://registry.npm.taobao.org

# 判断文件夹是否存在
# 如果存在,进行删除
echo "删除已经存在文件夹"
if [ -d "./dist/" ];then
    echo "执行:rm -rf ./dist"
    rm -rf ./dist
fi

# npm构建
echo "npm构建前段脚本"
npm run build

# 拷贝构建结果到资源路径下面





#mvn clean package