package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Boite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoiteRepository extends JpaRepository<Boite, Integer> {
    List<Boite> findByEtatNot(Integer etat);
    Boite findByIdBoiteAndEtatNot(Integer id, Integer etat);
}
