#application
This project includes 2 parts.
 
First part includes CRUD operations on user using jersey framework. Second part includes create and cancel operation on subscriptions.


The project has been implemented using spring-boot, Version 1.3.6.RELEASE.
 
Java8 is required for this application.
 
It uses H2 database.

To run the application:

mvn spring-boot:run

OR

java -jar tmp/myApp-ex_1.0-SNAPSHOT.war


Details about User operations:

To add user:

HTTP Method: POST 

URL: http://146.148.51.70:8080/users/add


Sample request: {"firstName":"Bob", "lastName":"Builder"}


To query user using firstName and lastName

HTTP Method: GET

URL: http://146.148.51.70:8080/users?firstName=Bob&lastName=Builder


To query user using id

HTTP Method: GET

URL: http://146.148.51.70:8080/users/id/{id}


To update a user

HTTP Method: PUT

URL: http://146.148.51.70:8080/users/id/{id}

Sample request: {"firstName":"Bob", "lastName":"Builder"}


To delete a user

HTTP Method: DELETE

URL: http://146.148.51.70:8080/users/id/{id}



Details about subscription operations:


To create a subscription:

HTTP Method: GET 

URL: http://146.148.51.70:8080/subscription/create?eventUrl={urlValue}


To cancel a subscription:

HTTP Method: GET 

URL: http://146.148.51.70:8080/subscription/cancel?eventUrl={urlValue}


