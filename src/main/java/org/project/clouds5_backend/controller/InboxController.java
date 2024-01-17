package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.Favoris;
import org.project.clouds5_backend.model.Inbox;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.service.InboxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping("inbox")
@RestController
public class InboxController {
    private InboxService inboxService;

    public InboxController(InboxService inboxService) {
        this.inboxService = inboxService;
    }
    @GetMapping("/{id}")
    ResponseEntity<Reponse<HashMap<Utilisateur, Inbox>>> getInbox(@PathVariable String id){
        Reponse<HashMap<Utilisateur, Inbox>> valiny = new Reponse<>();
        try {
            HashMap<Utilisateur, Inbox> data=inboxService.getMyInboxContent(id);
            if(data!=null){
                valiny.setData(data);
                valiny.setRemarque("Inbox trouvée");
                return ResponseEntity.ok().body(valiny);
        }else{
            valiny.setErreur("Inbox non trouvee");
            return ResponseEntity.status(404).body(valiny);
        }
        } catch (Exception e){
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }


//    @GetMapping("/inbox/{id}")
//    Reponse<List<Inbox>> getInbox(@PathVariable String id){
//        Reponse<List<Inbox>> valiny = new Reponse<>();
//        try {
//            valiny.setData(inboxService.getMyBox(id));
//        } catch (Exception e){
//            valiny.setErreur(e.getMessage());
//        }
//        return valiny;
//    }
}
