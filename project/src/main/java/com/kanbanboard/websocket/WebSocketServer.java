package com.kanbanboard.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(
        value="/events/{boardid}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class WebSocketServer{

    private Session session;
    private static final Set<WebSocketServer> socketEndpoint = new CopyOnWriteArraySet<>();
    private static final HashMap<String, String> users = new HashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("boardid") String boardid) throws IOException, EncodeException {
        this.session = session;
        socketEndpoint.add(this);
        users.put(session.getId(), boardid);

        Message msg = new Message();
        msg.setFrom(boardid);
        msg.setContent("Connected!");
        broadcast(msg);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        Message msg = new Message();
        msg.setFrom(users.get(session.getId()));
        broadcast(msg);
        System.out.println("["+session.getId()+"]: "+message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        socketEndpoint.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
        System.out.println("Connection has been  with: "+session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error reached!!!");
            System.out.println(throwable);
    }

    private static void broadcast(Message message)
            throws IOException, EncodeException {

        socketEndpoint.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
