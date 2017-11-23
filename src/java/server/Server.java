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
import servlets.login;

@ServerEndpoint("/echo")
public class Server {

    private static final Map<Session, String> usersAndTheirSessions = Collections.synchronizedMap(new HashMap<Session, String>());
    private static int i = 0;
    String username = null;
    String message;
    String jsonMessage;
    boolean init = true;
    boolean exists = false;

    @OnOpen
    public void onOpen(Session newSession) {
        //System.out.println(login.user);

        username = login.user;
        message = "connected";
        jsonMessage = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", username, message);

        for (Session out : newSession.getOpenSessions()) {

            sendToSession(out, jsonMessage);

        }
        
        usersAndTheirSessions.forEach((s, u) -> {

            if (username.equals(u)) {
                
                try {
                    newSession.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("username already exists");
            }

        });
        
        usersAndTheirSessions.put(newSession, username);
        System.out.println(username + ": " + message);

    }

    @OnMessage
    public void onMessage(String bericht, Session session) {
        JsonObject json = Json.createReader(new StringReader(bericht)).readObject();
        message = json.getString("message");
        jsonMessage = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", username, message);
        //System.out.println(username+": "+message);
        for (Session out : session.getOpenSessions()) {
            if (out != session) {
                sendToSession(out, jsonMessage);
            }

        }
        //username = json.getString("username");

        /*if (init) {
            usersAndTheirSessions.forEach((s, u) -> {

                if (username.equals(u)) {
                    exists = true;

                }

            });
            if (exists == false) {
                usersAndTheirSessions.put(session, username);
                message = username + " connected";
                jsonMessage = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", "server", message);
                //System.out.println(message);
                for (Session out : session.getOpenSessions()) {
                    if (out != session) {
                        sendToSession(out, jsonMessage);
                    }

                }
            } else {
                try {
                    session.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("username already exists");
            }

            init = false;

        } else {
            message = json.getString("message");
            jsonMessage = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", username, message);
            //System.out.println(username+": "+message);
            for (Session out : session.getOpenSessions()) {
                if (out != session) {
                    sendToSession(out, jsonMessage);
                }

            }
        }
         */
    }

    @OnClose
    public void onClose(Session oldSession) {
        //username = usersAndTheirSessions.get(oldSession);
        message = "disconnected";

        String json = String.format("{\"username\":\"%s\",\"message\":\"%s\"}", username, message);

        for (Session out : oldSession.getOpenSessions()) {
            if (out != oldSession) {
                sendToSession(out, json);
            }
        }
        usersAndTheirSessions.remove(oldSession);
        System.out.println(username + ": " + message);
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
