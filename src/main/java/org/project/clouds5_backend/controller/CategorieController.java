package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.repository.CategorieRepository;
import org.project.clouds5_backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategorieController {
    private final CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Categorie>>> getAllCategories() {
        Reponse<List<Categorie>> reponse = new Reponse<>();
        try {
            List<Categorie> categories = categorieService.getAllCategories();
            if (!categories.isEmpty()) {
                reponse.setData(categories);
                reponse.setRemarque("Liste des categories");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse<Categorie>> getCategorieById(@PathVariable Integer id) {
        Reponse<Categorie> reponse = new Reponse<>();
        try{
            Categorie categorie = categorieService.getCategorieById(id);
            if(categorie != null){
                reponse.setData(categorie);
                reponse.setRemarque("Categorie trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Categorie non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Categorie>> createCategorie(@Valid @RequestBody Categorie categorie) {
        Reponse<Categorie> reponse = new Reponse<>();
        try{
            Categorie categorieCreated = categorieService.createCategorie(categorie);
            if(categorieCreated != null){
                reponse.setData(categorieCreated);
                reponse.setRemarque("Categorie creee");
                return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
            }else{
                reponse.setErreur("Categorie non creee");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Categorie>> updateCategorieById(@PathVariable Integer id, @Valid @RequestBody Categorie categorie) {
        Reponse<Categorie> reponse = new Reponse<>();
        try{
            Categorie categorieUpdated = categorieService.updateCategorieById(id, categorie);
            if(categorieUpdated != null){
                reponse.setData(categorieUpdated);
                reponse.setRemarque("Categorie mise a jour");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Categorie non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Categorie>> deleteCategorieById(@PathVariable Integer id) {
        Reponse<Categorie> reponse = new Reponse<>();
        try{
            Categorie categorieDeleted = categorieService.deleteCategorieById(id);
            if(categorieDeleted != null){
                reponse.setData(categorieDeleted);
                reponse.setRemarque("Categorie supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Categorie non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }
}
