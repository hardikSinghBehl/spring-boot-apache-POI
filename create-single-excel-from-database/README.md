# Creating Single Excel Sheet from Existing Database Records

###### POC to demonstrate Generating Excel file (.xlsx) with single sheet with data coming from a database using Apache-POI library.

<center>
	<a target='_blank' href='https://killercroc.herokuapp.com/killercroc/swagger-ui.html'>CLICK HERE TO VIEW API DOCS (Deployed Swagger-UI)</a>
</center>

## Tech Stack

* Java 15
* Spring Boot
* Apache POI
* H2 Database
* Open-API (Swagger-ui)

## Flow

* 300 employees are saved into the database on startup with dummy values **(com.hardik.killercroc.bootstrap.EmployeeDataPopulationOnBootstrap.java)**
* Controller endpoint is provided to create additional employees in the system
* Controller endpoint to generate excel file representing all employees in the system

## Local Setup

* Install Java 15
* Install Maven

Recommended way is to use [sdkman](https://sdkman.io/) for installing both maven and java

Run the below commands in the core

```
mvn clean install
```

```
mvn spring-boot:run

```

server port is configured to 8089 which can be changed in application.properties file

Go to the below url to view swagger-ui (API docs)

```
http://localhost:8089/swagger-ui.html
```