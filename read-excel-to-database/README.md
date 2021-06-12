# Reading Validated Data From Excel Sheet And Storing In DB

###### POC to demonstrate reading data from excel file and storing records in database using apache POT library

<center>
	<a target='_blank' href='https://read-excel-spring-boot-poi.herokuapp.com/kofta/swagger-ui.html'>RUNNING APPLICATION (Swagger-ui)</a>
</center>


## Tech Stack

* Java 15
* Spring Boot
* Apache POI
* H2 Database
* Open-API (Swagger-ui)

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

server port is configured to 8089 and base path to /kofta which can be changed in application.properties file

Go to the below url to view swagger-ui (API docs)

```
http://localhost:8089/kofta/swagger-ui.html
```