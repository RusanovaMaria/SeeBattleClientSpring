package com.seebattleclient.websocket;

import com.google.gson.Gson;
import com.seebattleclient.message.Message;
import org.springframework.web.socket.TextMessage;

public class DefaultMessageHandler implements MessageHandler {

    private Gson gson;

    public DefaultMessageHandler() {
        gson = new Gson();
    }

    @Override
    public void handle(String context) {
        Message message = gson.fromJson(context, Message.class);
        System.out.println(message.getContext());
    }
}
