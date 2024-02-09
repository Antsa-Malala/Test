package org.project.clouds5_backend.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.project.clouds5_backend.model.*;
import org.project.clouds5_backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    private InboxService inboxService;
    private MobileService mobileService;
    private FirebaseMessagingService firebaseMessagingService;

    private UtilisateurService utilisateurService;

    public MessageService(MessageRepository messageRepository, InboxService inboxService,MobileService mobileService,FirebaseMessagingService firebaseMessagingService,UtilisateurService utilisateurService) {
        this.messageRepository = messageRepository;
        this.inboxService = inboxService;
        this.mobileService=mobileService;
        this.firebaseMessagingService=firebaseMessagingService;
        this.utilisateurService=utilisateurService;
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
    public Message insert(Message message) throws FirebaseMessagingException,Exception {
        try{
            Inbox inbox = inboxService.saveLastMessage(message);
            List<Mobile> ms=mobileService.getAllToken(message.getIdUtilisateur2());
            Utilisateur u=utilisateurService.getUtilisateurById(message.getIdUtilisateur2());
            System.out.println(message.getIdUtilisateur2()+" "+ms.size()+"zany ary");
            for(Mobile m : ms)
            {
                firebaseMessagingService.sendNotification(new NotificationMessage(m.getToken(),u.getNom().concat(" "+u.getPrenom()),"Vous avez un nouveau message."));
                System.out.println(m.getToken()+"ok lasa");
            }
            return messageRepository.insert(message);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
