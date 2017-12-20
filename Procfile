maven: cd ./server && mvn clean install
start: cd ./server && java -Dspring.profiles.active="heroku" -Dserver.port=$PORT -jar target/*.jar