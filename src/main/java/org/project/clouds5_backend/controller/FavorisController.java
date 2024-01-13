package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Favoris;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favoris")
public class FavorisController {
    private final FavorisService favorisService;

    @Autowired
    public FavorisController(FavorisService favorisService) {
        this.favorisService = favorisService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Favoris>>> getAllFavoris() {
        Reponse<List<Favoris>> reponse = new Reponse<>();
        try {
            List<Favoris> favoris = favorisService.getAllFavoris();
            if (!favoris.isEmpty()) {
                reponse.setData(favoris);
                reponse.setRemarque("Liste des favoris");
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
    public ResponseEntity<Reponse<Favoris>> getFavorisById(@PathVariable String id) {
        Reponse<Favoris> reponse = new Reponse<>();
        try{
            Favoris favoris = favorisService.getFavorisById(id);
            if(favoris != null){
                reponse.setData(favoris);
                reponse.setRemarque("Favoris trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Favoris non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Favoris>> createFavoris(@Valid @RequestBody Favoris favoris) {
        Reponse<Favoris> reponse = new Reponse<>();
        try{
            Favoris favoris1 = favorisService.createFavoris(favoris);
            if(favoris1 != null){
                reponse.setData(favoris1);
                reponse.setRemarque("Favoris creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Favoris non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Favoris>> updateFavorisById(@PathVariable String id, @Valid @RequestBody Favoris favoris) {
        Reponse<Favoris> reponse = new Reponse<>();
        try{
            Favoris favoris1 = favorisService.updateFavorisById(id, favoris);
            if(favoris1 != null){
                reponse.setData(favoris1);
                reponse.setRemarque("Favoris modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Favoris non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Favoris>> deleteFavorisById(@PathVariable String id) {
        Reponse<Favoris> reponse = new Reponse<>();
        try{
            Favoris favoris = favorisService.deleteFavorisById(id);
            if(favoris != null){
                reponse.setData(favoris);
                reponse.setRemarque("Favoris supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Favoris non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }

    }
}
