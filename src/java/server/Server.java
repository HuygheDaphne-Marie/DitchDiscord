package server;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class Server {

    private static final Map<Session, String> usersAndTheirSessions = Collections.synchronizedMap(new HashMap<Session, String>());
    private static int i = 0;
    String username = generateNewId();

    @OnOpen
    public void onOpen(Session newSession) {

        usersAndTheirSessions.put(newSession, username);

        String message = username + " connected";
        String json = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", "server", message);
        System.out.println(json);
        for (Session out : newSession.getOpenSessions()) {
            if (out != newSession) {
                sendToSession(out, json);
            }

        }

    }

    @OnMessage
    public void onMessage(String bericht, Session session) {
        JsonObject json = Json.createReader(new StringReader(bericht)).readObject();
        String message = json.getString("message");
        String jsonMessage = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", username, message);
        for (Session out : session.getOpenSessions()) {
            if (out != session) {
                sendToSession(out, jsonMessage);
            }

        }

    }

    @OnClose
    public void onClose(Session oldSession) {
        String message = username + " disconnected";

        String json = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", "server", message);
        usersAndTheirSessions.remove(oldSession);

        for (Session out : oldSession.getOpenSessions()) {
            if (out != oldSession) {
                sendToSession(out, json);
            }
        }
        System.out.println(message);
    }

    private synchronized String generateNewId() {
        i++;
        return "jos" + i;
    }

    private void sendToSession(Session out, String message) {
        try {
            if (out.isOpen()) {
                out.getBasicRemote().sendText(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
