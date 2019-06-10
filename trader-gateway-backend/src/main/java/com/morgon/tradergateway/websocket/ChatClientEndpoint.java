package com.morgon.tradergateway.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.morgon.tradergateway.model.Trader;
import com.morgon.tradergateway.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;

import javax.websocket.*;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description WebSocket client side实现
 * @version 1.0.0
 **/

@ClientEndpoint
public class ChatClientEndpoint {
    Session userSession = null;
    private MessageHandler messageHandler;

    @Autowired
    private TraderRepository traderRepository;

    public ChatClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider
                    .getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession
     *            the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {

        this.userSession = userSession;
        System.out.println("hhhhh");
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession
     *            the userSession which is getting closed.
     * @param reason
     *            the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a
     * client send a message.
     *
     * @param message
     *            The text message
     */
    @OnMessage
    public void onMessage(String message) {
        //if (this.messageHandler != null)
      //      this.messageHandler.handleMessage(message);
        System.out.println("Received!");
        System.out.println(message);

        if (!message.equals("Connected")){
            //JsonObject jobj = new Gson().fromJson(new Gson().toJson(message), JsonObject.class);

            JsonParser jsonParser = new JsonParser();
            JsonObject jobj = jsonParser.parse(message).getAsJsonObject();

            Long traderId = jobj.get("traderID").getAsLong();
            String tradername_ws = jobj.get("traderName").getAsString();
            System.out.println(tradername_ws);

            /*
            Trader trader = traderRepository.findTraderByTraderID(Long.valueOf(1));

            System.out.println("fuck1");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(trader));
            System.out.println("fuck");

            String tradername_ws = traderRepository.findTraderByTraderID(traderId).getTraderName();
            System.out.println(tradername_ws);*/

            for (String tradername : WebSocket.clients.keySet()) {

                if (tradername.equals(tradername_ws)){
                    System.out.println(message);
                    Session session = WebSocket.clients.get(tradername);
                    final RemoteEndpoint.Basic basic = session.getBasicRemote();
                    if (basic == null) {
                        return;
                    }
                    try {
                        basic.sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        else {
            System.out.println("Connected");
        }

    }

    /**
     * register message handler
     *
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}
