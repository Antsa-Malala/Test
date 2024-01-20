package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Energie;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.EnergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("energies")
public class EnergieController {
    private final EnergieService energieService;

    @Autowired
    public EnergieController(EnergieService energieService) {
        this.energieService = energieService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Energie>>> getAllEnergies() {
        Reponse<List<Energie>> reponse = new Reponse<>();
        try {
            List<Energie> energies = energieService.getAllEnergies();
            if (!energies.isEmpty()) {
                reponse.setData(energies);
                reponse.setRemarque("Liste des energies");
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
    public ResponseEntity<Reponse<Energie>> getEnergieById(@PathVariable Integer id) {
        Reponse<Energie> reponse = new Reponse<>();
        try{
            Energie energie = energieService.getEnergieById(id);
            if(energie != null){
                reponse.setData(energie);
                reponse.setRemarque("Energie trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Energie non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Energie>> createEnergie(@Valid @RequestBody Energie energie) {
        Reponse<Energie> reponse = new Reponse<>();
        try {
            Energie newEnergie = energieService.createEnergie(energie);
            if (newEnergie != null) {
                reponse.setData(newEnergie);
                reponse.setRemarque("Energie creee");
                return ResponseEntity.status(201).body(reponse);
            } else {
                reponse.setErreur("Energie non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Energie>> updateEnergieById(@PathVariable Integer id, @Valid @RequestBody Energie energie) {
        Reponse<Energie> reponse = new Reponse<>();
        try {
            Energie updatedEnergie = energieService.updateEnergieById(id, energie);
            if (updatedEnergie != null) {
                reponse.setData(updatedEnergie);
                reponse.setRemarque("Energie modifiee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Energie non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Energie>> deleteEnergieById(@PathVariable Integer id) {
        Reponse<Energie> reponse = new Reponse<>();
        try {
            Energie deletedEnergie = energieService.deleteEnergieById(id);
            if (deletedEnergie != null) {
                reponse.setData(deletedEnergie);
                reponse.setRemarque("Energie supprimee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Energie non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
