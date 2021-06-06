# Validate Excel Sheet Data For Errors And Send ErrorMessages as List<String>

###### POC to demonstrate Validating Excel file (.xlsx) data for error and in case of errors returning List of String detailin g out the error type with cell and row position.

<center>
	<a target='_blank' href='https://excel-validator-list-response.herokuapp.com/pyaaru/swagger-ui.html'>RUNNING APPLICATION (Swagger-ui)</a>
</center>

## Tech Stack

* Java 15
* Spring Boot
* Apache POI
* H2 Database
* Open-API (Swagger-ui)

## Application Flow

* User Downloads The Excel File Template from the API path /excel/template
* User fills in the value conforming to the downloaded format
* POST request to path /excel is made to validate the file
* Incase of errors, a List of String(s) is returned detailing out the error type with the row and cell index value

The following checks are made 
* values are not left blank
* email-id is of a valid type
* email-id does not already exist in the database (data populated in DB in bootstrap class)
* a valid numric value is entered in the salary column
* a valid name is entered in the fullname column 


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

server port is configured to 8070 and base path to /pyaaru which can be changed in application.properties file

Go to the below url to view swagger-ui (API docs)

```
http://localhost:8070/pyaaru/swagger-ui.html
```