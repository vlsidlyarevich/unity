# Unity
[![Build Status](https://travis-ci.org/vlsidlyarevich/unity.svg?branch=master)](https://travis-ci.org/vlsidlyarevich/unity)
[![Dependency Status](https://www.versioneye.com/user/projects/58028731a23d5200345545db/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58028731a23d5200345545db)
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
###Getting mongo
You can download and install mongo following instructions from (official site)[https://docs.mongodb.com/manual/administration/install-community/]
Or you can create and run docker image.
Type following commands in additional/docker/mongo folder.
* `docker build -t mongo .`
* `docker run -p 27017:27017 --name mongodb -d mongo`


###Running the application
* `mvn clean install`
* `java -jar target/*.jar`

###Debugging the application
* `mvn clean install`
* `java -Xdebug -Xrunjdwp:transport=dt_socket,address=50000,server=y -jar target/*.jar`

---
###Api reference
* GET `http://localhost:8080/api/workers` - get all workers profiles
* GET `http://localhost:8080/api/workers/id` - get worker profile by id 
* POST `http://localhost:8080/api/workers` - add new worker profile 
* PUT `http://localhost:8080/api/workers/` - update worker profile 
* DELETE `http://localhost:8080/api/workers/id` - delete worker profile by id 
* POST `/api/workers/search` - get workers profiles with filters 

Or you can import [json file](https://github.com/vlsidlyarevich/unity/blob/master/additional/insomnia/unity-api.json) to your Insomnia rest client
