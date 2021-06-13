# Creating Multiple Sheet Excel File from Existing Database Records

###### POC to demonstrate Generating Excel file (.xlsx) with multiple sheet with data coming from a database using Apache-POI library.

<center>
	<a target='_blank' href='https://bharta-poi-2x-generate.herokuapp.com/bharta/swagger-ui.html'>RUNNING APPLICATION (Swagger-ui)</a>
</center>

## Tech Stack

* Java 15
* Spring Boot
* Apache POI
* H2 Database
* Open-API (Swagger-ui)

## Application Flow

There are two entites 
* SuperHeroe
* MasterComic

Superheroes have one to many relationship with masterComic
* Batman belongs to detective comics
* Superman belongs to detective comics
* Detecive comic has List.of(Batman, Superman)

Data is populated into the H2 DB on application startup using @PostConstruct
* DataPopulationOnBootstrap.class

Excel file is generated and downloaded with
* Sheet containing records of Superheroes from detective comics
* Sheet containing records of Superheroes from marvel comics
* Sheet containing records of Superheroes from other comics

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

server port is configured to 8089 and base path to /bharta which can be changed in application.properties file

Go to the below url to view swagger-ui (API docs)

```
http://localhost:8089/bharta/swagger-ui.html
```

https://user-images.githubusercontent.com/69693621/121804452-15d4de00-cc64-11eb-9ddd-4f6215c1ed8f.mp4

