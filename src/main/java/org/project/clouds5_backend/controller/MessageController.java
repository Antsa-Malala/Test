package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.Inbox;
import org.project.clouds5_backend.model.Message;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("messages")
@RestController
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id1}/{id2}")
    ResponseEntity<Reponse<List<Message>>> getConversations(@PathVariable String id1, @PathVariable String id2){
        Reponse<List<Message>> valiny = new Reponse<>();
        try {
            List<Message> data=messageService.getAllByMpiresaka(id1, id2);
            if(data!=null)
            {
                valiny.setData(data);
                valiny.setRemarque("Conversations trouvées");
                return ResponseEntity.ok().body(valiny);
            }else{
                valiny.setErreur("Conversations non trouvees");
                return ResponseEntity.status(404).body(valiny);
            }
        } catch (Exception e){
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }


    @PostMapping
    public ResponseEntity<Reponse<Message>> insert(@RequestBody Message message){
        Reponse<Message> valiny = new Reponse<>();
        try {
            Message m=messageService.insert(message);
            if(m!=null)
            {
                valiny.setData(m);
                valiny.setRemarque("Message envoyé");
                return ResponseEntity.ok().body(valiny);
            }
            else{
                valiny.setErreur("Message non envoyé");
                return ResponseEntity.status(404).body(valiny);
            }
        } catch (Exception e){
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }
}
