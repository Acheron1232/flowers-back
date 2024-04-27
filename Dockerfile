FROM openjdk:21
COPY /build/libs/*.jar flowers.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/flowers.jar"]