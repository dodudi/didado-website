FROM openjdk:17-jdk-slim

ENV SPRING_PROFILES_ACTIVE=dev
WORKDIR /website
COPY app.jar .
CMD ["java", "-jar", "/website/app.jar"]