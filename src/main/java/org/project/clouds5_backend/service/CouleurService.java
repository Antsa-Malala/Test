package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Couleur;
import org.project.clouds5_backend.repository.CouleurRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CouleurService {
    private final CouleurRepository couleurRepository;

    public CouleurService(CouleurRepository couleurRepository) {
        this.couleurRepository = couleurRepository;
    }

    public List<Couleur> getAllCouleurs() {
        List<Couleur> couleurs = couleurRepository.findByEtatNot(10);
        if(couleurs.isEmpty()){
            return Collections.emptyList();
        }else {
            return couleurs;
        }
    }

    public Couleur getCouleurById(Integer id) {
        Couleur couleur = couleurRepository.findByIdCouleurAndEtatNot(id, 10);
        if(couleur == null){
            return null;
        }
        return couleur;
    }

    public Couleur createCouleur(Couleur couleur) {
        try{
            return couleurRepository.save(couleur);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Couleur updateCouleurById(Integer id, Couleur couleur) {
        Optional<Couleur> optionalCouleur = Optional.ofNullable(couleurRepository.findByIdCouleurAndEtatNot(id, 10));
        if(optionalCouleur.isPresent()){
            Couleur couleurToUpdate = optionalCouleur.get();
            couleurToUpdate.setNomCouleur(couleur.getNomCouleur());
            couleurRepository.save(couleurToUpdate);
            return couleurToUpdate;
        }else {
            throw new RuntimeException("Couleur non trouvee");
        }
    }

    public Couleur deleteCouleurById(Integer id) {
        Optional<Couleur> optionalCouleur = Optional.ofNullable(couleurRepository.findByIdCouleurAndEtatNot(id, 10));
        if(optionalCouleur.isPresent()){
            Couleur couleurToDelete = optionalCouleur.get();
            couleurToDelete.setEtat(10);
            couleurRepository.save(couleurToDelete);
            return couleurToDelete;
        }else {
            throw new RuntimeException("Couleur non trouvee");
        }
    }
}
