package com.morgon.tradergateway.websocket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.net.URI;

/**
 * @author Zhengyu Wu
 * @date 2019/6/10
 * @description WebSocket启动类实现
 * @version 1.0.0
 **/

@Component
public class WebSocketConmandLineRunner implements CommandLineRunner{

    @Override
    public void run(String... var1) throws Exception{
        //System.out.println("This will be execute when the project was started!");
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI("ws://202.120.40.8:30405/websocket/bd"));
        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
                String userName = jsonObject.getString("user");
                if (!"bot".equals(userName)) {
                    clientEndPoint.sendMessage(getMessage("Hello " + userName +", How are you?"));
                    // other dirty bot logic goes here.. :)
                }
            }
        });

        /*
        while (true) {
            clientEndPoint.sendMessage(getMessage("Hi There!!"));
            Thread.sleep(30000);
        }*/


    }

    private static String getMessage(String message) {
        return Json.createObjectBuilder()
                .add("user", "bot")
                .add("message", message)
                .build()
                .toString();
    }
}