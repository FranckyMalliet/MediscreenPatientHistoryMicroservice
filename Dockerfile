FROM openjdk:11
EXPOSE 80
VOLUME /tmp
ARG JAR_FILE=microservice-patient-history/target/microservicePatientHistory.jar
COPY ${JAR_FILE} microservicePatientHistory.jar
ENTRYPOINT ["java", "-jar", "/microservicePatientHistory.jar"]