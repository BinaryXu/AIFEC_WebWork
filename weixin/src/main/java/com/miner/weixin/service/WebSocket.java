package com.miner.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 洪峰
 * @create 2018-02-06 21:25
 **/
@Component
@Slf4j
@ServerEndpoint("/WebSocket")
public class WebSocket {

    private Session session;
    private String uuid;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();


    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【webSocket消息推送】有新的客户端连接,总数：{}。uuid={}",webSockets.size(),uuid);
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【webSocket消息推送】客户端断开连接,总数：{}",webSockets.size());
    }

    @OnMessage
    public void onMessage(String message){
        this.uuid = message;
        log.info("【webSocket消息推送】有新的消息：{}",message);
    }

    public void sendMessage(String message,String messageId){
        try {
            for (WebSocket webSocket:webSockets) {
                log.info("【webSocket消息推送】messageId：{}",messageId);
                if(messageId.equals(webSocket.uuid)){
                    log.info("【webSocket消息推送】广播消息：{}",webSocket.uuid);
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            }
        }catch (Exception e){
            log.error("【webSocket消息推送】消息广播异常{}",e);
        }
    }

}
