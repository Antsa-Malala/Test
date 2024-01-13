package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Marque;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marques")
public class MarqueController {
    private final MarqueService marqueService;

    @Autowired
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Marque>>> getAllMarques() {
        Reponse<List<Marque>> reponse = new Reponse<>();
        try {
            List<Marque> marques = marqueService.getAllMarques();
            if (!marques.isEmpty()) {
                reponse.setData(marques);
                reponse.setRemarque("Liste des marques");
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
    public ResponseEntity<Reponse<Marque>> getMarqueById(@PathVariable Integer id) {
        Reponse<Marque> reponse = new Reponse<>();
        try{
            Marque marque = marqueService.getMarqueById(id);
            if(marque != null){
                reponse.setData(marque);
                reponse.setRemarque("Marque trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Marque non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Marque>> createMarque(@Valid @RequestBody Marque marque) {
        Reponse<Marque> reponse = new Reponse<>();
        try{
            Marque marqueCreated = marqueService.createMarque(marque);
            if(marqueCreated != null){
                reponse.setData(marqueCreated);
                reponse.setRemarque("Marque creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Marque non creee");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Marque>> updateMarqueById(@PathVariable Integer id, @Valid @RequestBody Marque marque) {
        Reponse<Marque> reponse = new Reponse<>();
        try{
            Marque marqueToUpdate = marqueService.updateMarqueById(id, marque);
            if(marqueToUpdate != null){
                reponse.setData(marqueToUpdate);
                reponse.setRemarque("Marque mise a jour");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Marque non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Marque>> deleteMarqueById(@PathVariable Integer id) {
        Reponse<Marque> reponse = new Reponse<>();
        try{
            Marque marqueToDelete = marqueService.deleteMarqueById(id);
            if(marqueToDelete != null){
                reponse.setData(marqueToDelete);
                reponse.setRemarque("Marque supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Marque non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
