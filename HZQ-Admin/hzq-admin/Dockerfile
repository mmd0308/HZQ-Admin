FROM mmd0308/jre8:alpine
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar' \
    &&  echo $(date) > /image_built_at
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
