package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Boite;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.BoiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boites")
public class BoiteController {
    private final BoiteService boiteService;

    @Autowired
    public BoiteController(BoiteService boiteService) {
        this.boiteService = boiteService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Boite>>> getAllBoites() {
        Reponse<List<Boite>> reponse = new Reponse<>();
        try {
            List<Boite> boites = boiteService.getAllBoites();
            if (!boites.isEmpty()) {
                reponse.setData(boites);
                reponse.setRemarque("Liste des boites");
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
    public ResponseEntity<Reponse<Boite>> getBoiteById(@PathVariable Integer id) {
        Reponse<Boite> reponse = new Reponse<>();
        try{
            Boite boite = boiteService.getBoiteById(id);
            if(boite != null){
                reponse.setData(boite);
                reponse.setRemarque("Boite trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Boite non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Boite>> createBoite(@Valid @RequestBody Boite boite) {
        Reponse<Boite> reponse = new Reponse<>();
        try{
            Boite newBoite = boiteService.createBoite(boite);
            if(newBoite != null){
                reponse.setData(newBoite);
                reponse.setRemarque("Boite creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Boite non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Boite>> updateBoiteById(@PathVariable Integer id, @Valid @RequestBody Boite boite) {
        Reponse<Boite> reponse = new Reponse<>();
        try{
            Boite boiteUpdated = boiteService.updateBoiteById(id, boite);
            if(boiteUpdated != null){
                reponse.setData(boiteUpdated);
                reponse.setRemarque("Boite modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Boite non trouvee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Boite>> deleteBoiteById(@PathVariable Integer id) {
        Reponse<Boite> reponse = new Reponse<>();
        try{
            Boite boiteDeleted = boiteService.deleteBoiteById(id);
            if(boiteDeleted != null){
                reponse.setData(boiteDeleted);
                reponse.setRemarque("Boite supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Boite non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
