# webinar-reactive
=============
This example microservice uses Spring Cloud Stream functions to read from a kafka topic "movies", do some operations and then put the result on a kafka topic "goodmovies". It does so in a reactive way, using Project Reactor.

The functions are:
* logIncoming - logs the incoming message
* describeMovie - adds a description to the movie based on its name
* filter - only passes on movies above a certain rating and discards the rest
* logOutgoing - logs the outgoing message

For the functions describeMovie and filter there are example unit tests and integration tests. These tests are not meant to be exhaustive, just to serve as an example of how to easily implement such tests.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://start.spring.io) - Used to generate Spring service
* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) - Used for connecting to Kafka
* [Project Reactor](https://projectreactor.io) - Provides reactive non blocking streams
* [JUnit5](https://junit.org/junit5/) - Used for testing
* [Mockito](https://site.mockito.org) - Used for generating mocks in testing
