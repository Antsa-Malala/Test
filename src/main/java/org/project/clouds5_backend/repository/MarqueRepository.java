package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    List<Marque> findByEtatNot(Integer etat);
    Marque findByIdMarqueAndEtatNot(Integer id, Integer etat);
}
