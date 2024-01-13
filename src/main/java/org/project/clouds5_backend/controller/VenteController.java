package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Vente;
import org.project.clouds5_backend.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventes")
public class VenteController {
    private final VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Vente>>> getAllVentes() {
        Reponse<List<Vente>> reponse = new Reponse<>();
        try {
            List<Vente> ventes = venteService.getAllVentes();
            if (!ventes.isEmpty()) {
                reponse.setData(ventes);
                reponse.setRemarque("Liste des ventes");
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
    public ResponseEntity<Reponse<Vente>> getVenteById(@PathVariable String id) {
        Reponse<Vente> reponse = new Reponse<>();
        try{
            Vente vente = venteService.getVenteById(id);
            if(vente != null){
                reponse.setData(vente);
                reponse.setRemarque("Vente trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Vente non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Vente>> createVente(@RequestBody Vente vente) {
        Reponse<Vente> reponse = new Reponse<>();
        try{
            Vente venteCreated = venteService.createVente(vente);
            if(venteCreated != null){
                reponse.setData(venteCreated);
                reponse.setRemarque("Vente creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Vente non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Vente>> updateVenteById(@PathVariable String id, @RequestBody Vente vente) {
        Reponse<Vente> reponse = new Reponse<>();
        try{
            Vente venteUpdated = venteService.updateVenteById(id, vente);
            if(venteUpdated != null){
                reponse.setData(venteUpdated);
                reponse.setRemarque("Vente modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Vente non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Vente>> deleteVenteById(@PathVariable String id) {
        Reponse<Vente> reponse = new Reponse<>();
        try{
            Vente venteDeleted = venteService.deleteVenteById(id);
            if(venteDeleted != null){
                reponse.setData(venteDeleted);
                reponse.setRemarque("Vente supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Vente non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
