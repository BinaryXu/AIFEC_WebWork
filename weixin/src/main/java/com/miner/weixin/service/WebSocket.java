package com.miner.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@ServerEndpoint("/confirmWebSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("有客户端连接,当前连接总数{}",webSockets.size());
    }

}
