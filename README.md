# spring-cloud-stream-examples
Examples from the Takeaway Spring Cloud Stream / JUnit5 webinar of 26 May 2020

This repository contains five example projects:
* demoproducer- service that puts movies with a name and rating on Kafka topic "movies"
* demoprocessor - reads from "movies", filters out movies below a certain rating and puts on "goodmovies"
* democonsumer - service that reads from Kafka topic "goodmovies" and prints message contents to standard output
* testingdemo-non-reactive - more elaborate version of demoprocessor that demonstrates testing with JUnit5
* testingdemo-reactive - reactive version of testingdemo

If you have any questions or would like to know more about Takeaway.com, please send us an e-mail at open-source@takeaway.com
