package com.yiyang.reactiveChat.chatService.user;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Mono;

public interface UserRepository extends Repository<User, String> {
    Mono<User> findByUsername(String username);
}
