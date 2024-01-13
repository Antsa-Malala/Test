package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Refus;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.RefusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("refus")
public class RefusController {
    private final RefusService refusService;

    @Autowired
    public RefusController(RefusService refusService) {
        this.refusService = refusService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Refus>>> getAllRefus() {
        Reponse<List<Refus>> reponse = new Reponse<>();
        try {
            List<Refus> refus = refusService.getAllRefus();
            if (!refus.isEmpty()) {
                reponse.setData(refus);
                reponse.setRemarque("Liste des refus");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse<Refus>> getRefusById(@PathVariable String id) {
        Reponse<Refus> reponse = new Reponse<>();
        try{
            Refus refus = refusService.getRefusById(id);
            if(refus != null){
                reponse.setData(refus);
                reponse.setRemarque("Refus trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Refus non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Refus>> createRefus(@Valid @RequestBody Refus refus) {
        Reponse<Refus> reponse = new Reponse<>();
        try{
            Refus refus1 = refusService.createRefus(refus);
            if(refus1 != null){
                reponse.setData(refus1);
                reponse.setRemarque("Refus creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Refus non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Refus>> updateRefusById(@PathVariable String id, @Valid @RequestBody Refus refus) {
        Reponse<Refus> reponse = new Reponse<>();
        try{
            Refus refusUpdated = refusService.updateRefusById(id, refus);
            if(refusUpdated != null){
                reponse.setData(refusUpdated);
                reponse.setRemarque("Refus mofidiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Refus non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Refus>> deleteRefusById(@PathVariable String id) {
        Reponse<Refus> reponse = new Reponse<>();
        try{
            Refus refusDeleted = refusService.deleteRefusById(id);
            if(refusDeleted != null){
                reponse.setData(refusDeleted);
                reponse.setRemarque("Refus supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Refus non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }

    }
}
