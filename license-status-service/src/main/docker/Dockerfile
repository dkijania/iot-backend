FROM openjdk:8-jdk-alpine

ARG JAVA_OPTS=""
ENV JAVA_OPTS_ENV=$JAVA_OPTS

VOLUME /tmp
ADD "license-status-service/target/license-status-service-1.0-SNAPSHOT.jar" "app.jar"
RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS_ENV  -Djava.security.egd=file:/dev/./urandom -jar app.jar"]