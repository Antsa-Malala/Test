package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Marque;
import org.project.clouds5_backend.model.Modele;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("modeles")
public class ModeleController {
    private final ModeleService modeleService;

    @Autowired
    public ModeleController(ModeleService modeleService) {
        this.modeleService = modeleService;
    }

    @GetMapping("/modeleCategorieMarque/{id_categorie}/{id_marque}")
    public ResponseEntity<Reponse<List<Modele>>> getByCategorieMarque(@PathVariable Integer id_categorie, @PathVariable Integer id_marque) {
        Reponse<List<Modele>> valiny = new Reponse<List<Modele>>();
        try {
            List<Modele> data=modeleService.getByCategorieMarque(id_categorie, id_marque);
            if(data!=null)
            {
                valiny.setData(data);
                valiny.setRemarque("Modele par categorie par marque trouvee");
                return ResponseEntity.ok().body(valiny);
            }
            else{
                valiny.setErreur("Liste modele vide");
                return ResponseEntity.status(404).body(valiny);
            }
        } catch (Exception e) {
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }

    @GetMapping("/modeleCategorie/{id}")
    public ResponseEntity<Reponse<List<Modele>>> getByCategorie(@PathVariable Integer id) {
        Reponse<List<Modele>> valiny = new Reponse<List<Modele>>();
        try {
            List<Modele> data=modeleService.getByCategorie(id);
            if(data!=null)
            {
                valiny.setData(data);
                valiny.setRemarque("Modele par categorie trouvee");
                return ResponseEntity.ok().body(valiny);
            }
            else{
                valiny.setErreur("Liste modele vide");
                return ResponseEntity.status(404).body(valiny);
            }
        } catch (Exception e) {
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }

    @GetMapping("/modeleMarque/{id}")
    public ResponseEntity<Reponse<List<Modele>>> getByMarque(@PathVariable Integer id) {
        Reponse<List<Modele>> valiny = new Reponse<List<Modele>>();
        try {
            List<Modele> data=modeleService.getByMarque(id);
            if(data!=null)
            {
                valiny.setData(data);
                valiny.setRemarque("Modele par marque trouvee");
                return ResponseEntity.ok().body(valiny);
            }
            else{
                valiny.setErreur("Liste modele vide");
                return ResponseEntity.status(404).body(valiny);
            }
        } catch (Exception e) {
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }


    @GetMapping
    public ResponseEntity<Reponse<List<Modele>>> getAllModeles() {
        Reponse<List<Modele>> reponse = new Reponse<>();
        try {
            List<Modele> modeles = modeleService.getAllModeles();
            if (!modeles.isEmpty()) {
                reponse.setData(modeles);
                reponse.setRemarque("Liste des modeles");
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
    public ResponseEntity<Reponse<Modele>> getModeleById(@PathVariable Integer id) {
        Reponse<Modele> reponse = new Reponse<>();
        try{
            Modele modele = modeleService.getModeleById(id);
            if(modele != null){
                reponse.setData(modele);
                reponse.setRemarque("Modele trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Modele non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Modele>> createModele(@Valid @RequestBody Modele modele) {
        Reponse<Modele> reponse = new Reponse<>();
        try{
            Modele modele1 = modeleService.createModele(modele);
            if(modele1 != null){
                reponse.setData(modele1);
                reponse.setRemarque("Modele creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Modele non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Modele>> updateModeleById(@PathVariable Integer id, @Valid @RequestBody Modele modele) {
        Reponse<Modele> reponse = new Reponse<>();
        try{
            Modele modeleUpdated = modeleService.updateModeleById(id, modele);
            if(modeleUpdated != null){
                reponse.setData(modeleUpdated);
                reponse.setRemarque("Modele modifiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Modele non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Modele>> deleteModeleById(@PathVariable Integer id) {
        Reponse<Modele> reponse = new Reponse<>();
        try{
            Modele modeleDeleted = modeleService.deleteModeleById(id);
            if(modeleDeleted != null){
                reponse.setData(modeleDeleted);
                reponse.setRemarque("Modele supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Modele non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
