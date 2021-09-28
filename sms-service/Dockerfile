FROM openjdk:11
EXPOSE 8082
ADD target/*.jar sms-service.jar
ENTRYPOINT ["java","-jar","sms-service.jar"] eureka-service-registry
