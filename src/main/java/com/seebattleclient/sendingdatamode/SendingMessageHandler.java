package com.seebattleclient.sendingdatamode;

import com.google.gson.Gson;
import com.seebattleclient.message.Message;

public class SendingMessageHandler implements SendingDataHandler {
    private Gson gson;

    public SendingMessageHandler() {
        gson = new Gson();
    }

    @Override
    public String handle(String line) {
        Message messageRequest = new Message(line);
        String message = gson.toJson(messageRequest);
        return message;
    }
}
