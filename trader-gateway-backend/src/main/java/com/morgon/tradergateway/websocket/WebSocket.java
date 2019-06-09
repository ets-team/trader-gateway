package com.morgon.tradergateway.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morgon.tradergateway.model.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description WebSocket server side实现
 * @version 1.0.0
 **/

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        clients.put(username, session);
        String message = "欢迎用户[" + username + "] 来到聊天室！";
        logger.info(message);
        sendMessageAll(message);
    }

    /* @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        logger.info(message);
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;
        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            //群发
            if (socketMsg.getType() == 0) {
                sendMessageAll("用户[" + username + "] : " /*+ socketMsg.getMsg()*/);
            }
            //单聊.需要找到发送者和接受者.
            else{
                sendMessage(clients.get(socketMsg.getToUser()), "[" + username + "]" + "-> [" + socketMsg.getToUser() + "] : " + socketMsg.getMarketDepth());
                //sendMessage(clients.get(username), "[" + username + "]" + "-> [" + socketMsg.getToUser() + "] : " + socketMsg.getMsg());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //当前的Session 移除
        clients.remove(username);
        //并且通知其他人当前用户已经离开聊天室了
        sendMessageAll("用户[" + username + "] 已经离开聊天室了！");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }


    private static void sendMessageAll(String message) {
        clients.forEach((sessionId, session) -> sendMessage(session, message));
    }

    private static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
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
