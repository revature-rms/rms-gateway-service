FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ARG SPRING_ENV
ENV ACTIVE_SPRING_PROFILES=$SPRING_ENV
EXPOSE 8080
COPY target/${JAR_FILE} app.jar
COPY entrypoint.sh entrypoint.sh
ENTRYPOINT [ "/bin/sh", "./entrypoint.sh" ]