package com.yiyang.reactiveChat.chatService.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Service
public class OutboundChatService extends UserParsingHandshakeHandler {
    private static final Logger LOG = LoggerFactory.getLogger(OutboundChatService.class);

    private Flux<Message<String>> flux;
    private FluxSink<Message<String>> chatMessageSink;

    public OutboundChatService() {
            this.flux = Flux.<Message<String>>create(emitter -> this.chatMessageSink = emitter,
                    FluxSink.OverflowStrategy.IGNORE)
                            .publish()
                            .autoConnect();
    }

    /**
     * Listen to the RabbitMQ message broker. For every message, push it to the flux sink.
     * @param message
     */
    @StreamListener(ChatServiceStreams.BROKER_TO_CLIENT)
    public void listen(Message<String> message) {
        if(chatMessageSink != null) {
            LOG.info("Publishing " + message + " to websocket...");
            chatMessageSink.next(message);
        }
    }

    @Override
    public Mono<Void> handleInternal(WebSocketSession session) {
        return session
                .send(this.flux
                        .filter(s -> validate(s, getUser(session.getId())))
                        .map(this::transform)
                        .map(session::textMessage)
                        .log("outbound-wrap-as-websocket-message"))
                .log("outbound-publish-to-websocket");
    }

    /**
     * Validate user-to-user messages
     * If an '@' sign is detected at the beginning of the message, we deem this message user-specific, meaning only
     * matching user can receive the message. Otherwise, the message can be seen by everyone.
     * @param message
     * @param user
     * @return
     */
    private boolean validate(Message<String> message, String user) {
        if(message.getPayload().startsWith("@")) {
            String targetUser = message.getPayload().substring(1, message.getPayload().indexOf(" "));

            String sender = message.getHeaders().get(ChatServiceStreams.USER_HEADER, String.class);

            return user.equals(sender) || user.equals(targetUser);
        }
        else {
            return true;
        }
    }

    /**
     * Transform the message headings to indicate origin.
     * For example, a message from Tom to public: "Hello word" -> "Tom (to all): Hello world"
     * For example, a message from Tom to Eric: "@Eric Hello Eric" -> Tom (to you): Hello Eric"
     * @param message message payload
     * @return Transformed message
     */
    private String transform(Message<String> message) {
        String user = message.getHeaders().get(ChatServiceStreams.USER_HEADER, String.class);
        String payload = message.getPayload();

        if(payload.startsWith("@")) {
            String targetUser = payload.substring(1, payload.indexOf(" "));

            if(targetUser.equals(user)) {
                targetUser = "you";
            }

            return user + " (to " + targetUser + "): " + payload.substring(payload.indexOf(" ") + 1);
        }
        else {
            return user + " (to all): " + payload;
        }
    }
}
