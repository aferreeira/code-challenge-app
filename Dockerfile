FROM openjdk:8-jdk-alpine

ADD ./target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]