FROM openjdk:17-jdk-slim

ENV SPRING_PROFILES_ACTIVE=dev
WORKDIR /website
COPY app.jar .

EXPOSE 8081
CMD ["java", "-jar", "/website/app.jar"]