FROM openjdk:11

WORKDIR /app
COPY build/libs/sms-infrastructure-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
