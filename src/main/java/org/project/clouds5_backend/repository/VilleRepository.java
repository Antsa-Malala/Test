package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
    List<Ville> findByEtatNot(Integer etat);
    Ville findByIdVilleAndEtatNot(Integer id, Integer etat);
}


