FROM hub.c.163.com/mmd0308/java8:8u144
VOLUME /tmp
VOLUME /home/hzq/hblog/
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar' \
    &&  echo $(date) > /image_built_at \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezon
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
