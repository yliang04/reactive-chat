package com.yiyang.reactiveChat.chatService.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * The request sent from client has an "authorization" header, which Spring Security will use to authenticate.
 * If authenticated, this method will return the user principal
 */

@RestController
public class UserController {
    @GetMapping("/user")
    public Mono<String> getUser(Mono<Principal> user) {
        return user.map(Principal::getName);
    }
}

