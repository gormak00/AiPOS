FROM openjdk:11
ADD target/Html_server-0.0.1-SNAPSHOT.jar Client_server.jar
ENTRYPOINT ["java","-jar","/Client_server.jar"]
WORKDIR /Html_server/