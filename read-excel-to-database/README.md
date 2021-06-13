# Reading Validated Data From Excel Sheet And Storing In DB

###### POC to demonstrate reading data from excel file and storing records in database using apache POT library

<center>
	<a target='_blank' href='https://read-excel-spring-boot-poi.herokuapp.com/kofta/swagger-ui.html'>RUNNING APPLICATION (Swagger-ui)</a>
</center>

## Application Flow

* GET /excel/template API is hit to download excel template 
* POST /excel API is hit to validate data inside excel following above downloaded template
* Unique 6 digit code is provided when the excel is validated and is stored in a cache as a key, the value of the key is the hash of the validated excel file 
* POST /employee/bulk-upload API is is hit with the provided code and the validated excel file
* exception is thrown if code is invalid
* exception is thrown if file other than the validated one is given (comparing file hashes with stored hash corresponding to given code in cache)
* if code and excel files are valid, employee records are inserted into the database

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


https://user-images.githubusercontent.com/69693621/121804360-96dfa580-cc63-11eb-8c5b-ae14d15d2871.mp4

