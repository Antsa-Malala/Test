package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Voiture;
import org.project.clouds5_backend.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("voitures")
public class VoitureController {
    private final VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Voiture>>> getAllVoitures() {
        Reponse<List<Voiture>> reponse = new Reponse<>();
        try {
            List<Voiture> voitures = voitureService.getAllVoitures();
            if (!voitures.isEmpty()) {
                reponse.setData(voitures);
                reponse.setRemarque("Liste des voitures");
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
    public ResponseEntity<Reponse<Voiture>> getVoitureById(@PathVariable String id) {
        Reponse<Voiture> reponse = new Reponse<>();
        try{
            Voiture voiture = voitureService.getVoitureById(id);
            if(voiture != null){
                reponse.setData(voiture);
                reponse.setRemarque("Voiture trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Voiture non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Voiture>> createVoiture(@RequestBody Voiture voiture) {
        Reponse<Voiture> reponse = new Reponse<>();
        try{
            Voiture voitureCreated = voitureService.createVoiture(voiture);
            if(voitureCreated != null){
                reponse.setData(voitureCreated);
                reponse.setRemarque("Voiture creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Voiture non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Voiture>> updateVoitureById(@PathVariable String id, @Valid @RequestBody Voiture voiture) {
        Reponse<Voiture> reponse = new Reponse<>();
        try{
            Voiture voitureUpdated = voitureService.updateVoitureById(id, voiture);
            if(voitureUpdated != null){
                reponse.setData(voitureUpdated);
                reponse.setRemarque("Voiture modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Voiture non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Voiture>> deleteVoitureById(@PathVariable String id) {
        Reponse<Voiture> reponse = new Reponse<>();
        try{
            Voiture voitureDeleted = voitureService.deleteVoitureById(id);
            if(voitureDeleted != null){
                reponse.setData(voitureDeleted);
                reponse.setRemarque("Voiture supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Voiture non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
