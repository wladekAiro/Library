# Library api

`Requirements`
 ``````
 1. Java 8
 2. PostgreSQL version 12.*
 3. Maven 3

How to run
  Go to src/main/resources/application.yml and set your database credentials
  In the project root run $ mvn clean sprin-boot:run
  In your browser visit http://localhost:8082/swagger-ui/ to view the api documentation for this app

To initalize the app with sample data
   1. Students on post man make request request to GET http://localhost:8082/api/v1/students/init
   2. Students on post man make request request to GET http://localhost:8082/api/v1/books/init
