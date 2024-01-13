package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Couleur;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.CouleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couleurs")
public class CouleurController {
    private final CouleurService couleurService;

    @Autowired
    public CouleurController(CouleurService couleurService) {
        this.couleurService = couleurService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Couleur>>> getAllCouleurs() {
        Reponse<List<Couleur>> reponse = new Reponse<>();
        try {
            List<Couleur> couleurs = couleurService.getAllCouleurs();
            if (!couleurs.isEmpty()) {
                reponse.setData(couleurs);
                reponse.setRemarque("Liste des couleurs");
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
    public ResponseEntity<Reponse<Couleur>> getCouleurById(@PathVariable Integer id) {
        Reponse<Couleur> reponse = new Reponse<>();
        try{
            Couleur couleur = couleurService.getCouleurById(id);
            if(couleur != null){
                reponse.setData(couleur);
                reponse.setRemarque("Couleur trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Couleur non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Couleur>> createCouleur(@Valid @RequestBody Couleur couleur) {
        Reponse<Couleur> reponse = new Reponse<>();
        try{
            Couleur newCouleur = couleurService.createCouleur(couleur);
            if(newCouleur != null){
                reponse.setData(newCouleur);
                reponse.setRemarque("Couleur creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Couleur non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Couleur>> updateCouleurById(@PathVariable Integer id, @Valid @RequestBody Couleur couleur) {
        Reponse<Couleur> reponse = new Reponse<>();
        try{
            Couleur updatedCouleur = couleurService.updateCouleurById(id, couleur);
            if(updatedCouleur != null){
                reponse.setData(updatedCouleur);
                reponse.setRemarque("Couleur mise a jour");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Couleur non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Couleur>> deleteCouleurById(@PathVariable Integer id) {
        Reponse<Couleur> reponse = new Reponse<>();
        try{
            Couleur deletedCouleur = couleurService.deleteCouleurById(id);
            if(deletedCouleur != null){
                reponse.setData(deletedCouleur);
                reponse.setRemarque("Couleur supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Couleur non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
