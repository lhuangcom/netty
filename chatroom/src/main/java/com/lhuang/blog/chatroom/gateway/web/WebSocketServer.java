package com.lhuang.blog.chatroom.gateway.web;

import com.alibaba.fastjson.JSON;
import com.lhuang.blog.chatroom.api.pojo.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/chat/{userId}")
public class WebSocketServer {

    private static Map<String,WebSocketServer> onlineSessions = new ConcurrentHashMap<>();

    private Session session;

    private String userId;

    @OnOpen
    public void OnOpen(Session session, @PathParam("userId") String userId){
        if (userId == null || userId .equals("") ){
            System.out.println("出错了");
            return;
        }
        this.session = session;
        this.userId = userId;
        onlineSessions.put(userId,this);
        sendMessageToClient(new Message(Message.ENTER,"","","上线了",onlineSessions.size()));
    }

    @OnClose
    public void OnClose(Session session){
        onlineSessions.remove(this.userId);
        sendMessageToClient(new Message(Message.EXIT,"","","下线了",onlineSessions.size()));
    }

    @OnMessage
    public void OnMessage(Session session,String fromMessage){
        Message message = JSON.parseObject(fromMessage,Message.class);
        message.setState(Message.SPEAK);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToClient(message);
    }

    @OnError
    public void OnError(Session session,Throwable error){
        error.printStackTrace();
    }

    private void sendMessageToClient(Message message) {
        String text = JSON.toJSONString(message);

            if (message.getToUserId() != null && !"".equals(message.getToUserId()) ) {
                try {
                    //判断对方是否在线，不在线则session不存在，会报错；
                    onlineSessions.get(message.getToUserId()).session.getBasicRemote().sendText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            onlineSessions.forEach((id,webSocketServer) -> {
                try {
                    webSocketServer.session.getBasicRemote().sendText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

    }

}
