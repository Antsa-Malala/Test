package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Porte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PorteRepository extends JpaRepository<Porte, Integer> {
    List<Porte> findByEtatNot(Integer etat);
    Porte findByIdPorteAndEtatNot(Integer id, Integer etat);
}
