# SpringBoot + REST + Mongo + JUnit + Mockito

It's a sample REST project, build by using Spring Boot and using Mongo DB for persistance.

Project is build with [Spring Boot (1.5.3.RELEASE)](http://projects.spring.io/spring-boot/) 

## Table of Content ##

 1. [Description](#description)
 2. [How to Run](#how-to-run)
 3. [Project Structure](#project-structure)
 4. [Testing the Application](#testing-the-application)
 4. [Software](#software)
 
## Description
This repository comprises a sample app built with Spring Boot. All configurations are in java classes, no XML.

3 basic HTTP methods are implemented:

-**GET**
-**POST** &
-**DELETE**

BirdAppController.java is a REST controller.
IBirdAppService.java is service to cater controller requests.
Bird.java is a bean used as domain object. (check this bean to see how validations are placed on mandatory elements)
BirdRepository is an interface providing persistance with MongoDB.

## How to Run
there are 3 ways to run this application:
1. Launch it from eclipse
2. Build a jar and execute it
3. Create a war and deploy it on server

### Launchnig the Application from STS(eclipse)
1. import the application into eclipse as an existing maven project
2. right click on Application.java file and Run As > Java Application
![]({{site.baseurl}}/https://github.com/harshulvarshney/SpringBoot_REST_Mongo/blob/master/RunAsJavaApplication.jpg)

### Build a jar and execute it
1. go to project root directory, where pom.xml file is present
2. build the jar by usnig: **> mvn clean package**, verify that a jar is build insite target folder
3. execute the jar: **> java -jar target/sample-bird-app-1.0.jar**

We can direct start the application, even without building the jar by using below command:
> mvn spring-boot:run

## Project Structure

- **controller**: contains the REST end points for application.
- **service**: service layer of application, fulfilling business logic
- **repository**: It's an interface which allows various operations on Bird objects. It gets these operations by extending MongoRepository, which in turn extends the PagingAndSortingRepository interface defined in Spring Data Commons.

Spring boot usees some default properties for interacting with MongoDB, e.g:
> spring.data.mongodb.port=27017 # Mongo server port. Cannot be set with uri.
spring.data.mongodb.repositories.enabled=true # Enable Mongo repositories.
spring.data.mongodb.uri=mongodb://localhost/test

check all these default properties [here](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

## Testing the Application
For testing this application, I have used **JUnit, Mockito and Hamcrest**. All these dependencies are maintained by Spring Boot. Check pom.xml for "spring-boot-starter-test" dependency , it is taking care of all underlying dependencies and latest versions of these open source projects.

- **Testing controller**: BirdAppControllerTest contains various tests for BirdAppController. There are tests for both sucess and failure scenarios.
--MockMvc is used to mock various POST, GET and DELETE calls.
--Mockito is used to mock the BirdAppService and return the reponse from mocked service instead of hitting actual service and DB.

- **Testing Service**: BirdAppServiceTest contains various tests for BirdAppService. 
--Mockito is used here to mock the repository calls, instead of hitting the actual repository, the service requests will be returned by our mocked repository.

## Software

 - [Spring Tool Suite](https://spring.io/tools/sts)