package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Modele, Integer> {
    List<Modele> findByEtatNot(Integer etat);
    Modele findByIdModeleAndEtatNot(Integer id, Integer etat);
}
