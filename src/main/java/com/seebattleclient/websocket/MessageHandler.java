package com.seebattleclient.websocket;

import org.springframework.web.socket.TextMessage;

public interface MessageHandler {

    void handle(String message);
}
