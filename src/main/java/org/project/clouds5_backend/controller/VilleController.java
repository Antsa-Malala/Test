package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Ville;
import org.project.clouds5_backend.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("villes")
public class VilleController {
    private final VilleService villeService;

    @Autowired
    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Ville>>> getAllVilles() {
        Reponse<List<Ville>> reponse = new Reponse<>();
        try {
            List<Ville> villes = villeService.getAllVilles();
            if (!villes.isEmpty()) {
                reponse.setData(villes);
                reponse.setRemarque("Liste des villes");
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
    public ResponseEntity<Reponse<Ville>> getVilleById(@PathVariable Integer id) {
        Reponse<Ville> reponse = new Reponse<>();
        try{
            Ville ville = villeService.getVilleById(id);
            if(ville != null){
                reponse.setData(ville);
                reponse.setRemarque("Ville trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Ville non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Ville>> createVille(@Valid @RequestBody Ville ville) {
        Reponse<Ville> reponse = new Reponse<>();
        try{
            Ville villeCreated = villeService.createVille(ville);
            if(villeCreated != null){
                reponse.setData(villeCreated);
                reponse.setRemarque("Ville creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Ville non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Ville>> updateVilleById(@PathVariable Integer id, @Valid @RequestBody Ville ville) {
        Reponse<Ville> reponse = new Reponse<>();
        try{
            Ville villeUpdated = villeService.updateVilleById(id, ville);
            if(villeUpdated != null){
                reponse.setData(villeUpdated);
                reponse.setRemarque("Ville modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Ville non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Ville>> deleteVilleById(@PathVariable Integer id) {
        Reponse<Ville> reponse = new Reponse<>();
        try{
            Ville villeDeleted = villeService.deleteVilleById(id);
            if(villeDeleted != null){
                reponse.setData(villeDeleted);
                reponse.setRemarque("Ville supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Ville non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

}
