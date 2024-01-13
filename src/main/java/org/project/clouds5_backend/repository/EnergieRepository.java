package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Energie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergieRepository extends JpaRepository<Energie, Integer> {
    List<Energie> findByEtatNot(Integer etat);
    Energie findByIdEnergieAndEtatNot(Integer id, Integer etat);
}
