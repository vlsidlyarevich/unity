# Unity
[![Build Status](https://travis-ci.org/vlsidlyarevich/unity.svg?branch=master)](https://travis-ci.org/vlsidlyarevich/unity)
[![Dependency Status](https://www.versioneye.com/user/projects/58028731a23d5200345545db/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58028731a23d5200345545db)
[![Coverage Status](https://coveralls.io/repos/github/vlsidlyarevich/unity/badge.svg?branch=master)](https://coveralls.io/github/vlsidlyarevich/unity?branch=master)
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
* [Neo4j](https://neo4j.com/)
* [Docker](https://www.docker.com/)

---
[Takari maven wrapper](https://github.com/takari/maven-wrapper) is used.
If you don't have installed maven, or you don't want to install it, just type `./mvnw clean install`.

---
###Getting neo4j
You can download and install neo4j following instructions from [official site](https://neo4j.com/)
Or you can create and run docker image.
* `docker run -p 7474:7474 --name Neo4j neo4j:latest`

###Running the application
* `mvn clean install`
* `java -jar target/*.jar`

###Debugging the application
* `mvn clean install`
* `java -Xdebug -Xrunjdwp:transport=dt_socket,address=50000,server=y -jar target/*.jar`

---
###Api reference
####Worker profile api
* GET `http://localhost:8080/api/workers` - get all workers profiles
* GET `http://localhost:8080/api/workers` - get all workers profiles by name
* GET `http://localhost:8080/api/workers/id` - get worker profile by id 
* POST `http://localhost:8080/api/workers` - add new worker profile 
* PUT `http://localhost:8080/api/workers/id` - update worker profile by id
* DELETE `http://localhost:8080/api/workers/id` - delete worker profile by id 

####Vacancy api
* GET `http://localhost:8080/api/vacancies` - get all vacancies
* GET `http://localhost:8080/api/vacancies/id` - get vacancy by id 
* POST `http://localhost:8080/api/vacancies` - add new vacancy
* PUT `http://localhost:8080/api/vacancies/id` - update vacancy by id
* DELETE `http://localhost:8080/api/vacancies/id` - delete vacancy by id 

####Worker profile search api
* POST `/api/workers/search` - get workers profiles with filters 

####Worker profile delete api
* POST `/api/workers/delete` - delete workers 

####Image api
* GET `http://localhost:8080/api/images/id` - get image by id 
* POST `http://localhost:8080/api/images/upload` - upload new image 
* DELETE `http://localhost:8080/api/images/id` - delete image by id 

Or you can import [json file](https://github.com/vlsidlyarevich/unity/blob/master/additional/insomnia/unity-api.json) to your Insomnia rest client
