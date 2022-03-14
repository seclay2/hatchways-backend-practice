FROM openjdk:11-jre
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# default entrypoint
ENTRYPOINT java -jar app.jar