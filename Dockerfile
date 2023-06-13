FROM openjdk:11

ARG JAR_FILE=./sms-infrastructure/build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
