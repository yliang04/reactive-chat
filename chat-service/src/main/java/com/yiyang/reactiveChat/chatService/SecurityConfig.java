package com.yiyang.reactiveChat.chatService;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import java.net.URI;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(ALLOW_LIST).permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler("/"))
                .and()
                .build();
    }


    /**
     * Default logout would redirect to /login?logout. Add a custom handler to redirect to root.
     * @param uri
     * @return
     */
    private ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler logoutSuccessHandler = new RedirectServerLogoutSuccessHandler();
        logoutSuccessHandler.setLogoutSuccessUrl(URI.create(uri));

        return logoutSuccessHandler;
    }

    private static final String[] ALLOW_LIST = {
            "/index.html",
            "/",
            "/chat/**",
            "/topic/**",
            "/user",
            "/**.css",
            "/**.js",
            "/**.ico"
    };
}
