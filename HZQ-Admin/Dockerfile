FROM 10.27.213.66:5000/java:8u144
VOLUME /tmp
ADD hzq-admin/target/*.jar app.jar
RUN sh -c 'touch /app.jar' \
	&& echo $(date) > /image_built_at \
	&& /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
