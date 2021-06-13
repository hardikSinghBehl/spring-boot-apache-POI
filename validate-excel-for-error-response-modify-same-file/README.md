
# Validate Given Excel Sheet Data And Change Color of Cells With Error And Detail Out Error In Seperate Sheet

###### POC to validate data inside input excel sheet, modify the existing sheet to detail out the error and send it back as response to the user

<center>
	<a target='_blank' href='https://excel-validation-inside-file.herokuapp.com/durian/swagger-ui.html'>RUNNING APPLICATION (Swagger-ui)</a>
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
* Incase of no errors, success message is returned
* Incase of Errors, the cell containing invalid values are colored for the user and a seperate sheet is created in the same excel file detailing out the error messages and their position

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

server port is configured to 8070 and base path to /durian which can be changed in application.properties file

Go to the below url to view swagger-ui (API docs)

```
http://localhost:8070/durian/swagger-ui.html
```

https://user-images.githubusercontent.com/69693621/121804019-cc838f00-cc61-11eb-89bc-0ddce7bcb4f7.mp4
