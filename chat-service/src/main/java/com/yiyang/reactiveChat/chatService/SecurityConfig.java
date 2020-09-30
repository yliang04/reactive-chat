package com.yiyang.reactiveChat.chatService;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers(ALLOW_LIST).permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    private static final String[] ALLOW_LIST = {
            "/index.html",
            "/",
            "/chat",
            "/login",
            "/user",
            "/**.css",
            "/**.js"
    };
}
