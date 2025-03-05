# Birds API

## Overview
The Birds API is a RESTful service that provides JWT-based authentication and interacts with a SQL Server database. It is built using Spring Boot Security and Java 21.

## Prerequisites
- Java 21
- Maven
- SQL Server

## Setup

### Database
1. Ensure SQL Server is running.
2. Execute the SQL scripts located in the `src/main/resources` directory to set up the database schema and initial data.

### Configuration
1. Update the `application.properties` file with your database credentials and JWT settings.

```properties
spring.application.name=birds
server.port=9001

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=ufinet;encrypt=false;trustServerCertificate=false;hostNameInCertificate=localhost
spring.datasource.username=sa
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

jwt.secret=your_secret_key
jwt.expiration=86400000 # 24 hours in milliseconds
