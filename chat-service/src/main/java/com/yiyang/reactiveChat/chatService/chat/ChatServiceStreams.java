package com.yiyang.reactiveChat.chatService.chat;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ChatServiceStreams {
    String CLIENT_TO_BROKER = "clientTOBroker";
    String BROKER_TO_CLIENT = "brokerToClient";
    String USER_HEADER = "userHeader";

    @Output(CLIENT_TO_BROKER)
    MessageChannel clientToBroker();

    @Input(BROKER_TO_CLIENT)
    SubscribableChannel brokerToClient();
}
