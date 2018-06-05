package com.seebattleclient;

import com.google.gson.Gson;
import com.seebattleclient.message.Message;
import com.seebattleclient.websocket.DefaultMessageHandler;
import com.seebattleclient.websocket.WebSocketConnector;

import java.net.URI;
import java.util.Scanner;

public class Client {

    private String name;
    private WebSocketConnector webSocketConnector;
    private Gson gson;

    public Client(URI uri) {
        webSocketConnector = new WebSocketConnector(uri, new DefaultMessageHandler());
        gson = new Gson();
    }

    public void run() {
        webSocketConnector.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            Message messageRequest = new Message(line);
            String message = gson.toJson(messageRequest);
            webSocketConnector.sendMessage(message);
        }
    }


}
