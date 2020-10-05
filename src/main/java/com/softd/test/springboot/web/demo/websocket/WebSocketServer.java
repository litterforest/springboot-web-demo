package com.softd.test.springboot.web.demo.websocket;

import com.softd.test.springboot.web.demo.exception.WebSocketOprException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.DeferredResult;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-04
 */
@Component
@ServerEndpoint("/serverForWebSocket/{userId}")
@Slf4j
public class WebSocketServer {
    private static final AtomicLong onlineCount = new AtomicLong(0);
    private static final CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();
    private static final ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId;

    /**
     * 建立连接，记录会话、增加在线人数、记录总会话数
     *
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        log.info("session open: userid={}, sessionId={}", userId, session.getId());
        if (!StringUtils.isEmpty(userId)) {
            sessionMap.put(userId, session);
        }
        sessionSet.add(session);
        this.session = session;
        this.userId = userId;
        onlineCount.incrementAndGet();
    }

    /**
     * 关闭连接，清理相应资源。删除对应的session对象
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet();
        sessionSet.remove(session);
        sessionMap.remove(this.userId);
        this.session = null;
        this.userId = null;
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("session:" + session, throwable);
    }

    public static void sendMsgToUser(String userId, String msg) throws IOException, WebSocketOprException {
        Session session = sessionMap.get(userId);
        if (session == null) {
            throw new WebSocketOprException("用户不存在，消息发送失败");
        }
        session.getBasicRemote().sendText(msg);
    }

    public static long getOnlineCount() {
        return onlineCount.get();
    }
}
