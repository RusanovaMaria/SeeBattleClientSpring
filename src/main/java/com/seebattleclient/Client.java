package com.seebattleclient;

import com.seebattleclient.sendingdatamode.SendingDataHandler;
import com.seebattleclient.sendingdatamode.SendingMessageHandler;
import com.seebattleclient.websocket.DefaultMessageHandler;
import com.seebattleclient.websocket.WebSocketConnector;

import java.net.URI;
import java.util.Scanner;

public class Client {

    private SendingDataHandler dataHandler;
    private WebSocketConnector webSocketConnector;

    public Client(URI uri) {
        dataHandler = new SendingMessageHandler();
        webSocketConnector = new WebSocketConnector(uri, new DefaultMessageHandler(this));
    }

    public void run() {
        webSocketConnector.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String message = dataHandler.handle(line);
            sendMessageIfNotNull(message);
        }
    }

    private void sendMessageIfNotNull(String message) {
        if (message == null) {
            System.out.println("Данные введены неверно, попробуйте еще раз");
        } else {
            webSocketConnector.sendMessage(message);
        }
    }

    public void setDataHandler(SendingDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
