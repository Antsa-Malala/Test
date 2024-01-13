package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Ville;
import org.project.clouds5_backend.repository.VilleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public List<Ville> getAllVilles() {
        List<Ville> villes = villeRepository.findByEtatNot(10);
        if(villes.isEmpty()){
            return Collections.emptyList();
        }else{
            return villes;
        }
    }

    public Ville getVilleById(Integer id) {
        Ville ville = villeRepository.findByIdVilleAndEtatNot(id, 10);
        if(ville == null) {
            return null;
        }
        return ville;
    }

    public Ville createVille(Ville ville) {
        try{
            return villeRepository.save(ville);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Ville updateVilleById(Integer id, Ville ville) {
        Optional<Ville> optionalVille = Optional.ofNullable(villeRepository.findByIdVilleAndEtatNot(id, 10));
        if(optionalVille.isPresent()){
            Ville villeToUpdate = optionalVille.get();
            villeToUpdate.setNomVille(ville.getNomVille());
            villeRepository.save(villeToUpdate);
            return villeToUpdate;
        }else {
            throw new RuntimeException("Ville non trouvee");
        }
    }

    public Ville deleteVilleById(Integer id) {
        Optional<Ville> optionalVille = Optional.ofNullable(villeRepository.findByIdVilleAndEtatNot(id, 10));
        if(optionalVille.isPresent()){
            Ville villeToDelete = optionalVille.get();
            villeToDelete.setEtat(10);
            villeRepository.save(villeToDelete);
            return villeToDelete;
        }else {
            throw new RuntimeException("Ville non trouvee");
        }
    }
}
