FROM openjdk:14-jdk-alpine
VOLUME /tmp
RUN apk add maven
COPY . .
RUN mvn package
RUN find . -iname "ItemService*.jar"
ARG JAR_FILE=/target/ItemService-1.0-SNAPSHOT.jar
RUN cp ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
