FROM openjdk:11

ENV APP_HOME=/apps

ARG JAR_FILE_PATH=build/libs/sms-infrastructure-0.0.1-SNAPSHOT.jar

WORKDIR $APP_HOME

COPY $JAR_FILE_PATH app.jar

EXPOSE 8080

ENTRYPOING ["java", "-jar", "app.jar"]
