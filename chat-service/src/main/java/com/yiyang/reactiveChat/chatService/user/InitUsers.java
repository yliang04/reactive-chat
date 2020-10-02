package com.yiyang.reactiveChat.chatService.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitUsers {
    @Bean
    CommandLineRunner initializeUsers(ReactiveMongoOperations operations) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return args -> {
            operations.dropCollection(User.class).subscribe();

            operations.insert(new User(null, "Yiyang", encoder.encode("test"), new String[]{"admin"})).subscribe();
            operations.insert(new User(null, "Shuo", encoder.encode("test"), new String[]{"guest"})).subscribe();
            operations.insert(new User(null, "Tom", encoder.encode("test"), new String[]{"guest"})).subscribe();
            operations.insert(new User(null, "Eric", encoder.encode("test"), new String[]{"guest"})).subscribe();
        };
    }
}
