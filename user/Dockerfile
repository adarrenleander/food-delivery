FROM openjdk:11
EXPOSE 8088
ADD target/user-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]