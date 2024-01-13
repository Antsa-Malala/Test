package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        List<Categorie> categories = categorieRepository.findByEtatNot(10);
        if(categories.isEmpty()){
            return Collections.emptyList();
        }
        return categories;
    }

    public Categorie getCategorieById(Integer id) {
        Categorie categorie = categorieRepository.findByIdCategorieAndEtatNot(id, 10);
        if(categorie == null){
            return null;
        }
        return categorie;
    }

    public Categorie createCategorie(Categorie categorie) {
        try{
            return categorieRepository.save(categorie);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Categorie updateCategorieById(Integer id, Categorie categorie) {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(categorieRepository.findByIdCategorieAndEtatNot(id, 10));
        if(optionalCategorie.isPresent()){
            Categorie categorieToUpdate = optionalCategorie.get();
            categorieToUpdate.setNomCategorie(categorie.getNomCategorie());
            categorieRepository.save(categorieToUpdate);
            return categorieToUpdate;
        }else {
            throw new RuntimeException("Categorie non trouvee");
        }
    }

    public Categorie deleteCategorieById(Integer id) {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(categorieRepository.findByIdCategorieAndEtatNot(id, 10));
        if(optionalCategorie.isPresent()){
            Categorie categorieToDelete = optionalCategorie.get();
            categorieToDelete.setEtat(10);
            categorieRepository.save(categorieToDelete);
            return categorieToDelete;
        }else{
            throw new RuntimeException("Categorie non trouvee");
        }
    }
}
