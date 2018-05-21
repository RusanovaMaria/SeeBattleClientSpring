package websocket;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class WebSocketConnector {

    private Session userSession;
    private URI endpointURI;
    private MessageHandler messageHandler;

    public WebSocketConnector(URI endpointURI, MessageHandler messageHandler) {
        this.endpointURI = endpointURI;
        this.messageHandler = messageHandler;
    }

    public void connect() {
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("Вы подключились к серверу");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose() {
        System.out.println("Вы отключились от сервера");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (messageHandler != null) {
            messageHandler.handle(message);
        }
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
}
