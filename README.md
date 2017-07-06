# Unity

[![Dependency Status](https://www.versioneye.com/user/projects/58028731a23d5200345545db/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58028731a23d5200345545db)
[![Coverage Status](https://coveralls.io/repos/github/vlsidlyarevich/unity/badge.svg?branch=master)](https://coveralls.io/github/vlsidlyarevich/unity?branch=master)
### Angular 2 and Java 8 based Human resourse analysing tool

Also have a look at [Assistant programmer](https://github.com/Karina-Denisevich/Assistant-Programmer) for more Git-based workflow and [woodstock](https://github.com/solairerove/woodstock) lms.

---
##### Technologies:
* [Project Lombok](https://projectlombok.org/)
* [Angular 2](https://angular.io/)
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Angular-cli](https://cli.angular.io/)
* [MongoDB](https://www.mongodb.com/)
* [Docker](https://www.docker.com/)

---
[Takari maven wrapper](https://github.com/takari/maven-wrapper) is used.
If you don't have installed maven, or you don't want to install it, just type `./mvnw clean install`.

---
### Getting mongo
You can download and install mongo following instructions from [official site](https://docs.mongodb.com/manual/administration/install-community/)
Or you can create and run docker image.
Type following commands in additional/docker/mongo folder.
* `docker build -t mongo .`
* `docker run -p 27017:27017 --name mongodb -d mongo`

 
### Running the backend
* `mvn clean install`
* `java -jar target/*.jar`

### Running the client
* `cd unity-client`
* `npm i`
* `ng serve`

### Debugging the application
* `mvn clean install`
* `java -Xdebug -Xrunjdwp:transport=dt_socket,address=50000,server=y -jar target/*.jar`

---
### Api reference

Import [postman json file](https://github.com/vlsidlyarevich/unity/blob/master/additional/api/postman/unity%20api.postman_collection.json) to your Postman rest client
