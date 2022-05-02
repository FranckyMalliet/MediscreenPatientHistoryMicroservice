# Mediscreen PatientHistory Microservice

Mediscreen PatientHistory Microservice is a part of mediscreen application.

Made with Spring Boot application.

## Technologies
- Java 11
- Maven
- Spring
- JUnit
- Jacoco

## About this microservice 
### How to use
First of all, you have to create a database using MongoDB. 
The database must be called patienthistory.

Using the terminal, go to the pom.xml folder and use this command :

    mvn package -DskipTests

It will create a package named microservicePatientHistory-2.6.4 in the target folder.
You have to rename it microservicePatientHistory.

Then, go to the dockerfile folder and use this command :

    docker build -t microservicepatienthistory .

You can run now a new container with this image and use this microservice fully.

## Authors

Our code squad : Francky

## Licensing

This project was built under the Creative Commons Licence.
