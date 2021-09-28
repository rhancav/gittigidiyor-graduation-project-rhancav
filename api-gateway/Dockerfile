FROM openjdk:11
EXPOSE 9090
ADD target/*.jar api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]
