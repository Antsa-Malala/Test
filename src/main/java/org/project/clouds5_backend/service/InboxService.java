package org.project.clouds5_backend.service;
import org.project.clouds5_backend.model.Inbox;
import org.project.clouds5_backend.model.Message;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.repository.InboxRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InboxService {
    private InboxRepository inboxRepository;

    private UtilisateurService utilisateurService;

    public InboxService(InboxRepository inboxRepository, UtilisateurService utilisateurService) {
        this.inboxRepository = inboxRepository;
        this.utilisateurService = utilisateurService;
    }

    public List<Inbox> getMyBox(String iduser){
        return inboxRepository.getMyInbox(iduser);
    }

    public HashMap<Utilisateur, Inbox> getMyInboxContent(String idUser){
        List<Inbox> myInboxes = inboxRepository.getMyInbox(idUser);
        HashMap<Utilisateur, Inbox> result = new HashMap<>();
        for(Inbox inbox : myInboxes){
            String otherUser = inbox.getIdUtilisateur1();
            if(otherUser.equals(idUser)) otherUser = inbox.getIdUtilisateur2();
            result.put(utilisateurService.getUtilisateurById(otherUser), inbox);
        }
        return result;
    }

    public Inbox saveLastMessage(Message message){
        Inbox last = inboxRepository.ourLastMessage(message.getIdUtilisateur1(), message.getIdUtilisateur2());
        System.out.println("THe messages is " + last.getLastMessage() + " with id " + last.getId());
        if(last != null){
            Inbox inbox = inboxRepository.deleteInbox(message.getIdUtilisateur1(), message.getIdUtilisateur2());
            last.setDateMessage(message.getDateMessage());
            last.setLastMessage(message.getContenu());
            inboxRepository.save(last); // USE FIND AND REPLACE HERE

        } else {
            last = new Inbox();
            last.setIdUtilisateur1(message.getIdUtilisateur1());
            last.setIdUtilisateur2(message.getIdUtilisateur2());
            last.setDateMessage(message.getDateMessage());
            last.setLastMessage(message.getContenu());
            inboxRepository.save(last);
        }
        return last;
    }
}
