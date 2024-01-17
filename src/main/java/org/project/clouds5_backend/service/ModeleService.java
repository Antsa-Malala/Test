package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Modele;
import org.project.clouds5_backend.repository.ModeleRepository;
import org.springframework.stereotype.Service;
import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.model.Marque;
import org.project.clouds5_backend.repository.MarqueRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ModeleService {
    private final ModeleRepository modeleRepository;

    private MarqueService marqueService;

    private CategorieService categorieService;

    public ModeleService(ModeleRepository modeleRepository, MarqueRepository marqueRepository, MarqueService marqueService, CategorieService categorieService) {
        this.modeleRepository = modeleRepository;
        this.marqueService = marqueService;
        this.categorieService = categorieService;
    }

    public List<Modele> getByCategorieMarque(Integer idCategorie, Integer idMarque){
        Categorie categorie = categorieService.getCategorieById(idCategorie);
        Marque marque = marqueService.getMarqueById(idMarque);
        if(categorie != null && marque != null){
            return modeleRepository.getByCategorieMarque(idCategorie, idMarque);
        }
        return null;
    }

    public List<Modele> getByCategorie(Integer idCategorie){
        Categorie categorie = categorieService.getCategorieById(idCategorie);
        if(categorie != null){
            return modeleRepository.findByCategorie(categorie);
        }
        return null;
    }

    public List<Modele> getAllModeles() {
        List<Modele> modeles = modeleRepository.findByEtatNot(10);
        if(modeles.isEmpty()) {
            return Collections.emptyList();
        }
        return modeles;
    }

    public List<Modele> getByMarque(Integer idMarque){
        Marque marque = marqueService.getMarqueById(idMarque);
        if(marque != null){
            return modeleRepository.findByMarque(marque);
        }
        return null;
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
