package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Porte;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.PorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("portes")
public class PorteController {
    private final PorteService porteService;

    @Autowired
    public PorteController(PorteService porteService) {
        this.porteService = porteService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Porte>>> getAllPortes() {
        Reponse<List<Porte>> reponse = new Reponse<>();
        try {
            List<Porte> portes = porteService.getAllPortes();
            if (!portes.isEmpty()) {
                reponse.setData(portes);
                reponse.setRemarque("Liste des portes");
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
    public ResponseEntity<Reponse<Porte>> getPorteById(@PathVariable Integer id) {
        Reponse<Porte> reponse = new Reponse<>();
        try{
            Porte porte = porteService.getPorteById(id);
            if(porte != null){
                reponse.setData(porte);
                reponse.setRemarque("Porte trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Porte non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Porte>> createPorte(@Valid @RequestBody Porte porte) {
        Reponse<Porte> reponse = new Reponse<>();
        try {
            Porte newPorte = porteService.createPorte(porte);
            if (newPorte != null) {
                reponse.setData(newPorte);
                reponse.setRemarque("Porte creee");
                return ResponseEntity.status(201).body(reponse);
            } else {
                reponse.setErreur("Porte non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Porte>> updatePorteById(@PathVariable Integer id, @Valid @RequestBody Porte porte) {
        Reponse<Porte> reponse = new Reponse<>();
        try {
            Porte porteUpdated = porteService.updatePorteById(id, porte);
            if (porteUpdated != null) {
                reponse.setData(porteUpdated);
                reponse.setRemarque("Porte modifiee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Porte non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Porte>> deletePorteById(@PathVariable Integer id) {
        Reponse<Porte> reponse = new Reponse<>();
        try {
            Porte porteDeleted = porteService.deletePorteById(id);
            if (porteDeleted != null) {
                reponse.setData(porteDeleted);
                reponse.setRemarque("Porte supprimee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Porte non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
