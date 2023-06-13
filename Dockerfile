FROM openjdk:11-jdk

WORKDIR /usr/src/app

COPY sms-infrastructure/build/libs/sms-infrastructure-0.0.1-SNAPSHOT.jar sms-infrastructure/build/libs/sms-infrastructure-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","sms-infrastructure/build/libs/sms-infrastructure-0.0.1-SNAPSHOT.jar"]
