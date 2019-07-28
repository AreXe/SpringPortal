# BoardGames_Spring
"Senet Board Games" application that provides a possibility for their users to explore, like and share their favourite board games. Project made using Spring technology stack, along with REST API and Bootstrap interface.<br/>
Functionality includes i.a. registering new account, signing in system, updating account info with changing the password, admin panel to control user accounts and board games (CRUD), exploring and sharing board games.

### Stack of technologies
* Spring: Boot, Framework (MVC, DI), Data (Hibernate), Security
* Web: Bootstrap
* Tests: JUnit Jupiter

## Live demo
Application is available @ Heroku:
* [Senet Board Games app](https://senetbg.herokuapp.com/)

## Index view of Senet app
![senet-view](images/senet-view.png)

## RESTful service usage
Available methods for board games CRUD service:
* `GET` /api/boardgames - return board games list as JSON
* `GET` /api/boardgames/{title} - return board games list by title
* `GET` /api/boardgames/{id} - return board game by given ID
* `POST` /api/boardgames - add new board game (for Admin)
* `PUT` /api/boardgames/{id} - update board game by given ID (for Admin)
* `DELETE` /api/boardgames/{id} - delete board game by given ID (for Admin)

## How to run the application
The project includes Spring Boot Maven Plugin, so using this command will build and run the application:
```
mvn spring-boot:run
```
Application properties are available under the file `application.properties` which is needed to change the credentials and host of provided database.

## Heroku deployment example
Article: https://devcenter.heroku.com/articles/war-deployment
```
mvn package
heroku plugins:install java
heroku war:deploy target/bgames.war -a senetbg
```
### Credentials
Login to admin account using:
* E-mail: admin@gmail.com
* Password: admin