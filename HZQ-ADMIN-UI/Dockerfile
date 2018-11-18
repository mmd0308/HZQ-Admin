FROM nginx:1.15.5
MAINTAINER hzqing
# 创建根目录
WORKDIR /usr/share/nginx/hzq-admin-ui
WORKDIR /etc/nginx/logs
# 拷贝构建文件
COPY ./dist/ /usr/share/nginx/hzq-admin-ui/
# 拷贝配置文件到容器
COPY nginx.conf /etc/nginx/nginx.conf
# 镜像导出端口
EXPOSE 80