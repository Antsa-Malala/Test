package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Refus;
import org.project.clouds5_backend.repository.RefusRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RefusService {
    private final RefusRepository refusRepository;

    public RefusService(RefusRepository refusRepository) {
        this.refusRepository = refusRepository;
    }

    public List<Refus> getAllRefus() {
        List<Refus> refus = refusRepository.findAll();
        if(refus.isEmpty()){
            return Collections.emptyList();
        }else{
            return refus;
        }
    }

    public Refus getRefusById(String id) {
        Refus refus = refusRepository.findById(id).orElse(null);
        if(refus == null) {
            return null;
        }
        return refus;
    }

    public Refus createRefus(Refus refus) {
        try{
            return refusRepository.save(refus);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Refus updateRefusById(String id, Refus refus) {
        Optional<Refus> optionalRefus = Optional.ofNullable(refusRepository.findById(id).orElse(null));
        if (optionalRefus.isPresent()) {
            Refus refusToUpdate = optionalRefus.get();
            refusToUpdate.setDateRefus(refus.getDateRefus());
            refusToUpdate.setUtilisateur(refus.getUtilisateur());
            refusToUpdate.setAnnonce(refus.getAnnonce());
            refusRepository.save(refusToUpdate);
            return refusToUpdate;
        }else{
            throw new RuntimeException("Refus non trouvee");
        }
    }

    public Refus deleteRefusById(String id) {
        Optional<Refus> optionalRefus = Optional.ofNullable(refusRepository.findById(id).orElse(null));
        if(optionalRefus.isPresent()){
            Refus refusToDelete = optionalRefus.get();
            refusRepository.delete(refusToDelete);
            return refusToDelete;
        }else{
            throw new RuntimeException("Refus non trouvee");
        }
    }

    public String getNextValSequence()
    {
        return refusRepository.getNextValSequence();
    }

}
