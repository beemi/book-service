# Spring boot book service

## Description
This is a simple spring boot application that exposes a REST API to manage a book library.

## Requirements

- Java 17
- Maven
- Docker
- Docker-compose
- Postman

## How to run

### Run the application
```shell
mvn spring-boot:run
```

### Run the application with docker-compose
```shell
docker-compose up -d
```

## Swagger

Access Open API documentation at: http://localhost:8090/swagger-ui/index.html

API documentation is also available in JSON format at: http://localhost:8090/v3/api-docs

![Swagger UI](docs/images/swagger-api.png)
