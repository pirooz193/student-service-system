FROM openjdk:18
COPY . /student-service
WORKDIR /student-service
CMD java -jar StudentserviceApplication-0.0.1-SNAPSHOT.jar