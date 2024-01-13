package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    List<Categorie> findByEtatNot(Integer etat);
    Categorie findByIdCategorieAndEtatNot(Integer id, Integer etat);
}
