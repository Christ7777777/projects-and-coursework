package drivewayparking.app.service;

import drivewayparking.app.entity.Message;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository repository;

//    public List<Message> getMessagesBySenderReceiver(Long senderId, Long receiverId) {
//        return repository.findBySenderReceiver(senderId, receiverId);
//    }

    public Integer saveMessage(Message message){

        repository.save(message);
        return Status.OK.getValue();
    }

    public Integer deleteMessage(Long id) {

        Message existingMessage = repository.findById(id).orElse(null);
        if (existingMessage != null) {
            repository.deleteById(id);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }
}
