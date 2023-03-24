FROM openjdk:17
ADD build/libs/V-SELL-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=file:/home/ubuntu/properties/application.properties"]