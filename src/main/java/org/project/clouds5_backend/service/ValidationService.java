package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Validation;
import org.project.clouds5_backend.repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {
    private final ValidationRepository validationRepository;

    public ValidationService(ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    public List<Validation> getAllValidations() {
        List<Validation> validations = validationRepository.findAll();
        if(validations.isEmpty()){
            return Collections.emptyList();
        }else{
            return validations;
        }
    }

    public Validation getValidationById(String id) {
        Validation validation = validationRepository.findById(id).orElse(null);
        if(validation == null) {
            return null;
        }
        return validation;
    }

    public Validation createValidation(Validation validation) {
        try{
            return validationRepository.save(validation);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Validation updateValidationById(String id, Validation validation) {
        Optional<Validation> optionalValidation = Optional.ofNullable(validationRepository.findById(id).orElse(null));
        if (optionalValidation.isPresent()) {
            Validation validationToUpdate = optionalValidation.get();
            validationToUpdate.setDateValidation(validation.getDateValidation());
            validationToUpdate.setUtilisateur(validation.getUtilisateur());
            validationToUpdate.setAnnonce(validation.getAnnonce());
            validationRepository.save(validationToUpdate);
            return validationToUpdate;
        }else{
            throw new RuntimeException("Validation non trouvee");
        }
    }

    public Validation deleteValidationById(String id) {
        Optional<Validation> optionalValidation = Optional.ofNullable(validationRepository.findById(id).orElse(null));
        if(optionalValidation.isPresent()){
            Validation validationToDelete = optionalValidation.get();
            validationRepository.delete(validationToDelete);
            return validationToDelete;
        }else{
            throw new RuntimeException("Validation non trouvee");
        }
    }

    public String getNextValSequence()
    {
        return validationRepository.getNextValSequence();
    }

}
