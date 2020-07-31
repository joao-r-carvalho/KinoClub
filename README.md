# KinoClub

To run:
 mvn spring-boot:run
 
There is an instance hosted currently at https://kinoclub-1595860726231.azurewebsites.net/

Requires instancing mongoURI in the application properties file in

src\main\resources

prop

spring.data.mongodb.uri




To deploy:

az login

mvn clean package

mvn azure-webapp:deploy




