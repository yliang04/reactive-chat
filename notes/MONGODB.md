# MongoDb Note

* In the code I never have to explicitly create or specify a mongo repository. Spring is
able to figure out from the dependencies I have in build.gradle. 

* In my `UserRepository` interface, spring sees that my repository returns a `Mono` so
it knows that it needs to find a reactive version of repository bean. Since I'm using
Mongo, I will need
    ```
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    ```
  
  However, since I'm also using `MongoOperation`, it seems that I also need
  ```
  implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
  ``` 