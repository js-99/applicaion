#application
This project includes 2 parts. 
First part includes CRUD operations on user using jersey framework. Second part includes create and cancel operation on subscriptions.

The project has been implemented using spring-boot, Version 1.4.0.RELEASE. 
Java8 is required for this application. 
It uses H2 database.

Details about User operations:

To add user:
HTTP Method: POST 
URL: http://localhost:8080/users/add

Sample request: {"firstName":"Bob", "lastName":"Builder"}

To query user using firstName and lastName
HTTP Method: GET
URL: http://localhost:8080/users?firstName=Bob&lastName=Builder

To query user using id
HTTP Method: GET
URL: http://localhost:8080/users/id/{id}

To update a user
HTTP Method: PUT
URL: http://localhost:8080/users/id/{id}

Sample request: {"firstName":"Bob", "lastName":"Builder"}

To delete a user
HTTP Method: DELETE
URL: http://localhost:8080/users/id/{id}



Details about subscription operations:

To create a subscription:
HTTP Method: POST 
URL: http://localhost:8888/api/notification/subscription/create?eventUrl={urlValue}

To cancel a subscription:
HTTP Method: POST 
URL: http://localhost:8888/api/notification/subscription/cancel?eventUrl={urlValue}

