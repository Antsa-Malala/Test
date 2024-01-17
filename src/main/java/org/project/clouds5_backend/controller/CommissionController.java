package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.model.Commission;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.repository.CategorieRepository;
import org.project.clouds5_backend.service.CategorieService;
import org.project.clouds5_backend.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commissions")
public class CommissionController {
    private final CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Commission>>> getAllCommission() {
        Reponse<List<Commission>> reponse = new Reponse<>();
        try {
            List<Commission> commissions = commissionService.getAllCommissions();
            if (!commissions.isEmpty()) {
                reponse.setData(commissions);
                reponse.setRemarque("Liste des commissions");
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
    public ResponseEntity<Reponse<Commission>> getCommissionById(@PathVariable Integer id) {
        Reponse<Commission> reponse = new Reponse<>();
        try{
            Commission commission = commissionService.getCommissionById(id);
            if(commission != null){
                reponse.setData(commission);
                reponse.setRemarque("Commission trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Commission non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Commission>> createCommission(@Valid @RequestBody Commission commission) {
        Reponse<Commission> reponse = new Reponse<>();
        try{
            Commission commissionCreated = commissionService.createCommission(commission);
            if(commissionCreated != null){
                reponse.setData(commissionCreated);
                reponse.setRemarque("Commission creee");
                return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
            }else{
                reponse.setErreur("Commission non creee");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Commission>> updateCommissionById(@PathVariable Integer id, @Valid @RequestBody Commission commission) {
        Reponse<Commission> reponse = new Reponse<>();
        try{
            Commission commissionUpdated = commissionService.updateCommissionById(id, commission);
            if(commissionUpdated != null){
                reponse.setData(commissionUpdated);
                reponse.setRemarque("Commission mise a jour");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Commission non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Commission>> deleteCommissionById(@PathVariable Integer id) {
        Reponse<Commission> reponse = new Reponse<>();
        try{
            Commission commissionDeleted = commissionService.deleteCommissionById(id);
            if(commissionDeleted != null){
                reponse.setData(commissionDeleted);
                reponse.setRemarque("Commission supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Commission non trouvee");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse);
        }
    }
}
