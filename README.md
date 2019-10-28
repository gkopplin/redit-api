# redit-api
## Technologies used
- [PostgreSQL](https://www.postgresql.org/)
    PostgreSQL is an open-source relational database. We used a PostgreSQL database to store information on users, posts, and comments, and to run SQL queries on our data. 
- [Hibernate](https://hibernate.org/)
    Hibernate is a group of tools for connecting a Java project to an external database. We relied on the Hibernate ORM to create Java objects based on data from our PostgreSQL database and Hibernate Validator to specify model validation constraints via the application layer.
- [Spring](https://spring.io/)
    Spring is a back-end framework for structuring a Java application. Through the use of 'beans', Spring allows data to be passed efficiently between components of the application.
- [Spring Security](https://spring.io/projects/spring-security)
    Spring Security is an authentication framework for Spring applications.
- [JUnit](https://junit.org/junit4/)
    JUnit is a simple testing framework for Java projects. It is meant for implementing repeatable unit tests.
- [Mockito](https://site.mockito.org/)
    Mockito is a framework for mocking objects in unit tests for Java projects.
- [Jackson](https://github.com/FasterXML/jackson)
    Jackson is a library for parsing JSON objects.
- [Json Web Token](https://jwt.io/)
    Json Web Token, or JWT, is collection of libraries to facilitate authentication via JSON tokens across various platforms. 

## Approach
- We started with the ER diagram, we learned what entities and columns we needed to create, and the relationship between them.
- We used pivotaltracker to create epics, then disscussed and added user story under each epics one by one.
- We set up the environment first, created several config files, and application properties.
- When we set up the project, we start with the controller first, so that we know what parameter we need to pass in, and what output we need to get.


## Major obstacles
- Get to know how does each layer work and connect with each other.
- Reading frontend code and link frontend with backend.
- How different layers implement testing. How to assertEquals of a mocked value with actually passed in function value.
- Jwt technology and implementation.

## Planning documentation
Our primary tool for planning and mapping out the timeline for the project was Pivotal Tracker. [Epics and User Stories](https://www.pivotaltracker.com/n/projects/2407479) for this project.

Before we started implementing our plan, we also created an [Entity Relationship Diagram](https://github.com/gkopplin/redit-api/wiki/ERD) using Adobe XD.

## Installation instructions for dependencies
The dependencies listed in the pom.xml file will automatically be imported by Maven. No additional instillation is required.





