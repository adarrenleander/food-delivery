FROM openjdk:11
EXPOSE 8087
ADD target/tracking-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]