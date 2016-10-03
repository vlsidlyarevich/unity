# Unity
###Angular and java based Human resourse management system

Module of integration with [woodstock](https://github.com/solairerove/woodstock) lms.

---
#####Technologies:
* [Project Lombok](https://projectlombok.org/)
* [AngularJS](https://angularjs.org/)
* [Angular Material](https://material.angularjs.org)
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Gulp](http://gulpjs.com/)
* [Bower](https://bower.io/)

---
[Takari maven wrapper](https://github.com/takari/maven-wrapper) is used.
If you don't have installed maven, or you don't want to install it, just type `./mvnw clean install`.

---
###Running the application
* Type `mvn spring-boot:run` to run the backend part.
* Go to src/main/webapp and then type `npm i` and `npm start` to start an angularjs view

---
###Api reference
* GET `http://localhost:8080/api/workers` - get all workers profiles
* GET `http://localhost:8080/api/workers/id` - get worker profile by id 
* POST `http://localhost:8080/api/workers` - add new worker profile 
* PUT `http://localhost:8080/api/workers/` - update worker profile 
* DELETE `http://localhost:8080/api/workers/id` - delete worker profile by id 
* POST `/api/workers/search` - get workers profiles with filters 
