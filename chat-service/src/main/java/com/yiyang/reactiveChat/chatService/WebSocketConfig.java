package com.yiyang.reactiveChat.chatService;

import com.yiyang.reactiveChat.chatService.chat.InboundChatService;
import com.yiyang.reactiveChat.chatService.chat.OutboundChatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig {
    @Bean
    HandlerMapping webSocketMapping(InboundChatService inboundChatService,
                                     OutboundChatService outboundChatService)
    {
        Map<String, WebSocketHandler> urlMap = new HashMap<>();
        urlMap.put("/chat/chatMessage.new", inboundChatService);
        urlMap.put("/topic/chatMessage.new", outboundChatService);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(20);
        mapping.setUrlMap(urlMap);

        return mapping;
    }

    @Bean
    WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
