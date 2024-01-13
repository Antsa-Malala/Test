package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("utilisateurs")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Utilisateur>>> getAllUtilisateurs() {
        Reponse<List<Utilisateur>> reponse = new Reponse<>();
        try {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
            if (!utilisateurs.isEmpty()) {
                reponse.setData(utilisateurs);
                reponse.setRemarque("Liste des utilisateurs");
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
    public ResponseEntity<Reponse<Utilisateur>> getUtilisateurById(@PathVariable String id) {
        Reponse<Utilisateur> reponse = new Reponse<>();
        try{
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            if(utilisateur != null){
                reponse.setData(utilisateur);
                reponse.setRemarque("Utilisateur trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Utilisateur non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Utilisateur>> createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        Reponse<Utilisateur> reponse = new Reponse<>();
        try{
            Utilisateur utilisateur1 = utilisateurService.createUtilisateur(utilisateur);
            if(utilisateur1 != null){
                reponse.setData(utilisateur1);
                reponse.setRemarque("Utilisateur cree");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Utilisateur non cree");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Utilisateur>> updateUtilisateurById(@PathVariable String id, @Valid @RequestBody Utilisateur utilisateur) {
        Reponse<Utilisateur> reponse = new Reponse<>();
        try{
            Utilisateur utilisateurUpdated = utilisateurService.updateUtilisateurById(id, utilisateur);
            if(utilisateurUpdated != null){
                reponse.setData(utilisateurUpdated);
                reponse.setRemarque("Utilisateur modifie");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Utilisateur non trouve");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Utilisateur>> deleteUtilisateurById(@PathVariable String id) {
        Reponse<Utilisateur> reponse = new Reponse<>();
        try{
            Utilisateur utilisateurDeleted = utilisateurService.deleteUtilisateurById(id);
            if(utilisateurDeleted != null){
                reponse.setData(utilisateurDeleted);
                reponse.setRemarque("Utilisateur supprime");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Utilisateur non trouve");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
