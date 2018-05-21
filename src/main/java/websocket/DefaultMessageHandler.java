package websocket;

import com.google.gson.Gson;
import response.MessageResponse;

public class DefaultMessageHandler implements MessageHandler {

    private Gson gson;

    public DefaultMessageHandler() {
        gson = new Gson();
    }

    @Override
    public void handle(String message) {
        MessageResponse messageResponse = gson.fromJson(message, MessageResponse.class);
        System.out.println(messageResponse.getMessage());
    }
}
