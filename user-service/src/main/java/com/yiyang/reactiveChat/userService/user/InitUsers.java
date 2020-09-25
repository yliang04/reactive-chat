package com.yiyang.reactiveChat.userService.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

@Configuration
public class InitUsers {
    @Bean
    CommandLineRunner initializeUsers(ReactiveMongoOperations operations) {
        return args -> {
            operations.dropCollection(User.class).subscribe();

            operations.insert(new User(null, "yiyang", "test", new String[]{"admin"})).subscribe();
            operations.insert(new User(null, "shuo", "test", new String[]{"guest"})).subscribe();
        };
    }
}
