package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Modele;
import org.project.clouds5_backend.repository.ModeleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ModeleService {
    private final ModeleRepository modeleRepository;

    public ModeleService(ModeleRepository modeleRepository) {
        this.modeleRepository = modeleRepository;
    }

    public List<Modele> getAllModeles() {
        List<Modele> modeles = modeleRepository.findByEtatNot(10);
        if(modeles.isEmpty()) {
            return Collections.emptyList();
        }
        return modeles;
    }

    public Modele getModeleById(Integer id) {
        Modele modele = modeleRepository.findByIdModeleAndEtatNot(id, 10);
        if(modele == null) {
            return null;
        }
        return modele;
    }

    public Modele createModele(Modele modele) {
        try{
            return modeleRepository.save(modele);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Modele updateModeleById(Integer id, Modele modele) {
        Optional<Modele> optionalModele = Optional.ofNullable(modeleRepository.findByIdModeleAndEtatNot(id, 10));
        if(optionalModele.isPresent()){
            Modele modeleToUpdate = optionalModele.get();
            modeleToUpdate.setNomModele(modele.getNomModele());
            modeleToUpdate.setMarque(modele.getMarque());
            modeleToUpdate.setCategorie(modele.getCategorie());
            modeleRepository.save(modeleToUpdate);
            return modeleToUpdate;
        }else {
            throw new RuntimeException("Modele non trouvee");
        }
    }

    public Modele deleteModeleById(Integer id) {
        Optional<Modele> optionalModele = Optional.ofNullable(modeleRepository.findByIdModeleAndEtatNot(id, 10));
        if(optionalModele.isPresent()){
            Modele modeleToDelete = optionalModele.get();
            modeleToDelete.setEtat(10);
            modeleRepository.save(modeleToDelete);
            return modeleToDelete;
        }else {
            throw new RuntimeException("Modele non trouvee");
        }
    }
}
