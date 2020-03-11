FROM openjdk:14-jdk-alpine
VOLUME /tmp
RUN apk add git maven
ARG BRANCH=master
ARG REPOSITORY_USER=SGmuwa
ARG REPOSITORY_NAME=Web-develop-backend-7-semester
ADD https://api.github.com/repos/${REPOSITORY_USER}/${REPOSITORY_NAME}/git/refs/heads/${BRANCH} version.json
RUN git clone -b${BRANCH} https://github.com/${REPOSITORY_USER}/${REPOSITORY_NAME}.git /gitrepository
WORKDIR /gitrepository
RUN mvn package
RUN find . -iname "ItemService*.jar"
WORKDIR /
ARG JAR_FILE=/gitrepository/target/ItemService-1.0-SNAPSHOT.jar
RUN cp ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
