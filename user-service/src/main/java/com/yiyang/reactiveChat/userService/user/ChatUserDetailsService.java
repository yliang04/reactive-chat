package com.yiyang.reactiveChat.userService.user;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChatUserDetailsService implements ReactiveUserDetailsService {
    private UserRepository userRepository;

    public ChatUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Transform my own user domain object to spring user domain which implements UserDetails
    @Override
    public Mono<UserDetails> findByUsername(String name) {
        return userRepository.findByUsername(name).map(user -> new User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles())
        ));
    }
}
