# reactive-chat

### Introduction
This is a Spring Boot application I built while learning Spring Cloud Stream and 
Spring Security. Here is a quick walk through of the features

* Structured with Eureka and Spring Cloud Config allow easier scale.
* Basic HTTP authentication via Spring Security.
* MongoDB in the backend with Spring Data Mongo Reactive
* Angular frontend
* Websocket communication

### Video Demo
Watch a quick demo of this video here at this Youtube  [link](https://youtu.be/fnrLu-vumns).

### TODO
* Scaling
    * Move the login services to the user0service program.
* Security
    * Add/implement sticky sessions.
    * Move away from using HTTP basic authentication
* New features
    * New User Register function
    * Enhance chat layout. For own messages, maybe I can populate it on the right
    hand side instead.
    * Allow image upload
    * (Maybe) Angular Material instead of bootstrap
* Semantics
    * Currently I'm still using the annotation based configuration for Spring Cloud
    Stream. Newer versions of Spring Cloud encourages functional style with Spring
    Cloud Function.
