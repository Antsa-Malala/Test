package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Validation;
import org.project.clouds5_backend.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("validations")
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Validation>>> getAllValidations() {
        Reponse<List<Validation>> reponse = new Reponse<>();
        try {
            List<Validation> validations = validationService.getAllValidations();
            if (!validations.isEmpty()) {
                reponse.setData(validations);
                reponse.setRemarque("Liste des validations");
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
    public ResponseEntity<Reponse<Validation>> getValidationById(@PathVariable String id) {
        Reponse<Validation> reponse = new Reponse<>();
        try{
            Validation validation = validationService.getValidationById(id);
            if(validation != null){
                reponse.setData(validation);
                reponse.setRemarque("Validation trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Validation non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Validation>> createValidation(@Valid @RequestBody Validation validation) {
        Reponse<Validation> reponse = new Reponse<>();
        try{
            Validation validation1 = validationService.createValidation(validation);
            if(validation1 != null){
                reponse.setData(validation1);
                reponse.setRemarque("Validation creee");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Validation non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Validation>> updateValidationById(@PathVariable String id, @Valid @RequestBody Validation validation) {
        Reponse<Validation> reponse = new Reponse<>();
        try{
            Validation validationUpdated = validationService.updateValidationById(id, validation);
            if(validationUpdated != null){
                reponse.setData(validationUpdated);
                reponse.setRemarque("Validation mofidiee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Validation non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Validation>> deleteValidationById(@PathVariable String id) {
        Reponse<Validation> reponse = new Reponse<>();
        try{
            Validation validationDeleted = validationService.deleteValidationById(id);
            if(validationDeleted != null){
                reponse.setData(validationDeleted);
                reponse.setRemarque("Validation supprimee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Validation non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }

    }
}
