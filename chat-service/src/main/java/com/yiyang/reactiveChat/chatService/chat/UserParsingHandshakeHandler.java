package com.yiyang.reactiveChat.chatService.chat;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class UserParsingHandshakeHandler implements WebSocketHandler {

    /**
     * This user map stores mapping between websocket sessions and username
     */
    private final Map<String, String> userMap;

    UserParsingHandshakeHandler() {
        this.userMap = new HashMap<>();
    }

    /**
     * This handler extracts the username belongs to this websocket session from the "username" query parameter from
     * websocket url.
     * For example, a url may look like "ws://localhost:8200/chat/chatMessage.new?username=Tom"
     * @param session
     * @return
     */
    @Override
    public final Mono<Void> handle(WebSocketSession session) {
        this.userMap.put(session.getId(),
                Stream.of(session
                            .getHandshakeInfo()
                            .getUri()
                            .getQuery()
                            .split("&"))
                    .map(s -> s.split("="))
                    .filter(strings -> strings[0].equals("username"))
                    .findFirst()
                    .map(strings -> strings[1])
                    .orElse(""));

        return handleInternal(session);
    }

    abstract protected Mono<Void> handleInternal(WebSocketSession session);

    String getUser(String id) {
        return userMap.get(id);
    }
}
