package com.seebattleclient.websocket;

import com.google.gson.Gson;
import com.seebattleclient.Client;
import com.seebattleclient.message.Message;
import com.seebattleclient.sendingdatamode.SendingCoordinatesHandler;
import com.seebattleclient.sendingdatamode.SendingMessageHandler;
import org.springframework.web.socket.TextMessage;

public class DefaultMessageHandler implements MessageHandler {

    private Gson gson;
    private Client client;

    public DefaultMessageHandler(Client client) {
        gson = new Gson();
        this.client = client;
    }

    @Override
    public void handle(String context) {
        Message message = gson.fromJson(context, Message.class);
        String messageSrt = message.getContent();
        if(messageSrt.equals("Начало расстановки игровых объектов")) {
            client.setDataHandler(new SendingCoordinatesHandler());
        }
        if (messageSrt.equals("Игровые объекты успешно установлены")) {
            client.setDataHandler(new SendingMessageHandler());
        }
        System.out.println(messageSrt);
    }
}
