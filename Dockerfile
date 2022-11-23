FROM openjdk:8-slim
ENV java_opts=""
ENV java_args=""
LABEL maintainer="ricomayo.8sp@gmail.com"
WORKDIR /app
COPY target/darirumah*.jar /app/app.jar
ENTRYPOINT exec java $java_opts -jar app.jar $java_args
EXPOSE 8080