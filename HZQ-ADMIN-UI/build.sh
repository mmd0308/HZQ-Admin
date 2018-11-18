#!/bin/bash
# 导入依赖
echo '导入依赖'
npm install --registry=https://registry.npm.taobao.org

# 判断文件夹是否存在
# 如果存在,进行删除
echo "删除已经存在文件夹"
if [ -d "./dist/" ];then
    echo "执行:rm -rf ./dist"
    rm -rf ./dist
fi

echo 'npm构建'
npm run build

# 构建docker镜像
echo '构建docker镜像'
docker build -t hzq-admin-ui:latest .