package websocket;

import com.google.gson.Gson;
import message.Message;

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
