package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Favoris;
import org.project.clouds5_backend.model.Inbox;
import org.project.clouds5_backend.model.Message;
import org.project.clouds5_backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    private InboxService inboxService;

    public MessageService(MessageRepository messageRepository, InboxService inboxService) {
        this.messageRepository = messageRepository;
        this.inboxService = inboxService;
    }

    //  Read message
    public List<Message> getAllByMpiresaka(String idUser1, String idUser2){
        List<Message> messages = messageRepository.getMessageUsers(idUser1, idUser2);
        if(messages.isEmpty())
        {
            return Collections.emptyList();
        }
        else{
            return messages;
        }
    }
    //    Insert message
    public Message insert(Message message) throws Exception{
        try{
            Inbox inbox = inboxService.saveLastMessage(message);
            return messageRepository.insert(message);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
