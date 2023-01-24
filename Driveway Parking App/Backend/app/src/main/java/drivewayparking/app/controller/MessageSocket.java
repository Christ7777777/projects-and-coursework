package drivewayparking.app.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import drivewayparking.app.dto.MessageHistory;
import drivewayparking.app.dto.MessageRequest;
import drivewayparking.app.entity.Message;
import drivewayparking.app.entity.User;
import drivewayparking.app.repository.MessageRepository;
import drivewayparking.app.repository.UserRepository;
import drivewayparking.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@ServerEndpoint(value = "/message/{id}")
public class MessageSocket {

    private static MessageRepository messageRepository;

    private static UserRepository userRepository;

    @Autowired
    public void setMessageRepository(MessageRepository repository) {
        messageRepository = repository;
    }

    @Autowired
    public void setUserRepository(UserRepository repository) {
        userRepository = repository;
    }

    private static Map<Session, Long> sessionUsernameMap = new Hashtable<>();
    private static Map<Long, Session> usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(MessageSocket.class);

    @OnOpen
    public List<List<MessageHistory>> onOpen(Session session, @PathParam("id") Long id) throws IOException {
        logger.info("Entered into Open");

        // store connecting user info
        sessionUsernameMap.put(session, id);
        usernameSessionMap.put(id, session);

        User user = userRepository.findById(id).orElse(null);
        List<List<MessageHistory>> chatHistory = new ArrayList<>();

        if (user != null) {
            List<Long> chats = messageRepository.findUserHistory(id);

            for (Long chat : chats) {
                List<Message> temp = messageRepository.getChatHistory(id, chat);
                List<MessageHistory> hist = messagesToHistory(temp, id, chat);

                for (MessageHistory m : hist) {
                    try {
                        String s = m.getName() + "\n" + m.getSentTime() + "\n" +
                                m.isSent() + "\n" + m.getMessage();
                        usernameSessionMap.get(id).getBasicRemote().sendText(s);
                    }
                    catch(IOException e) {
                        logger.info("Exception: " + e.getMessage().toString());
                        e.printStackTrace();
                    }
                }
                chatHistory.add(hist);
            }
        }

        return chatHistory;
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // handle new messages
        logger.info("\n Entered into Message. Got message: " + message);

        if (message != null) {
            String[] temp = message.split("\n", 2);
            Long id = Long.parseLong(temp[0].strip());
            send(temp[1], id);
            messageRepository.save(saveMessage(temp[1], sessionUsernameMap.get(session), id));
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        // remove user connection information
        Long id = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(id);

        // broadcast user has disconnected
//        User user = userRepository.findById(id).orElse(null);
//        String message = user.getFirstName() + " has disconnected.";
//        send(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // error handling
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }

    private void send(String message, Long receiver) {
        sessionUsernameMap.forEach((session, id) -> {
            if (id == receiver) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.info("Exception: " + e.getMessage().toString());
                    e.printStackTrace();
                }
            }
        });
    }

    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, id) -> {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.info("Exception: " + e.getMessage().toString());
                    e.printStackTrace();
                }
        });
    }

    private Message saveMessage(String m, Long id, Long otherId) {
        Message msg = new Message();
        msg.setBody(m);
        msg.setSender(userRepository.findById(id).orElse(null));
        msg.setReceiver(userRepository.findById(otherId).orElse(null));
        return msg;
    }

    private List<MessageHistory> messagesToHistory(List<Message> messages, Long id, Long otherId) {
        List<MessageHistory> history = new ArrayList<>();

        for (Message m : messages) {
            history.add(messageToHistory(m, id, otherId));
        }

        return history;
    }
    private MessageHistory messageToHistory(Message message, Long id, Long otherId) {
        MessageHistory history = new MessageHistory();
        history.setMessage(message.getBody());
        history.setSentTime(message.getSent());
        history.setOtherId(otherId);
        User otherUser = userRepository.findById(otherId).orElse(null);
        String name = otherUser.getFirstName() + " " + otherUser.getLastName();
        history.setName(name);
        if (message.getSender().getId() == id) {
            history.setSent(true);
        }
//        System.out.println("Added message with body:" + message.getBody());
//        if (history.isSent()) {
//            System.out.println("Sent by: " + id);
//        }
//        else {
//            System.out.println("Sent by: " + otherId);
//        }

        return history;
    }
}
