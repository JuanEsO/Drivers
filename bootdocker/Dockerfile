FROM openjdk:17-oracle
COPY drivers-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/drivers-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8089
ENTRYPOINT ["java","-jar","/usr/src/bootdocker/drivers-0.0.1-SNAPSHOT.jar"]