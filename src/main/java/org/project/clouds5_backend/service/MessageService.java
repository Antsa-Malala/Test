package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Inbox;
import org.project.clouds5_backend.model.Message;
import org.project.clouds5_backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

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
        return messageRepository.getMessageUsers(idUser1, idUser2);
    }

    //    Insert message
    public Message insert(Message message){
        Inbox inbox = inboxService.saveLastMessage(message);
        return messageRepository.insert(message);
    }
}
