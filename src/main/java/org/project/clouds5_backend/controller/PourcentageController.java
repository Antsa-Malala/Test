package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Commission;
import org.project.clouds5_backend.model.Pourcentage;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.PourcentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pourcentage")
public class PourcentageController {
    private final PourcentageService pourcentageService;

    @Autowired
    public PourcentageController(PourcentageService pourcentageService) {
        this.pourcentageService = pourcentageService;
    }

    @GetMapping
    public ResponseEntity<Reponse<Double>> getValeur() {
        Reponse<Double> reponse = new Reponse<>();
        try {
            double valiny = pourcentageService.getValeur();
            if (valiny!=0) {
                reponse.setData(valiny);
                reponse.setRemarque("Pourcentage trouvée");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Pourcentage non définie");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Pourcentage>> createPourcentage(@Valid @RequestBody Pourcentage pourcentage) {
        Reponse<Pourcentage> reponse = new Reponse<>();
        try{
            Pourcentage pourcentageCreated = pourcentageService.createPourcentage(pourcentage);
            if(pourcentageCreated != null){
                reponse.setData(pourcentageCreated);
                reponse.setRemarque("Pourcentage inseree");
                return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
            }else{
                reponse.setErreur("Pourcentage non inseree");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }
}