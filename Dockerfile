
FROM java:8
VOLUME /tmp
ADD target/restApi-0.0.1-SNAPSHOT.jar restApi.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /restApi.jar"]