package server;

import java.io.IOException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class Server {
    private String user;
    @OnOpen
    public void onOpen(Session session) {
        /*InetAddress addr;
        addr=InetAddress.getLocalHost();
        String hostname=addr.getHostName(); //displays pcname of user*/

        System.out.println(session.getId() + " has opened a connection");

        try {
            
                session.getBasicRemote().sendText("Connection Established");
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JsonObject json = Json.createReader(new StringReader(message)).readObject();
//        user=json.getString("username");
        String msg=json.getString("message");
        System.out.println(user+": "+msg);
        try {
            for (Session s : session.getOpenSessions()) {
                s.getBasicRemote().sendText(user+": "+msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
       try {
            for (Session s : session.getOpenSessions()) {
                s.getBasicRemote().sendText(user+" disconnected");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Session " + session.getId() + " has ended");
    }
}
