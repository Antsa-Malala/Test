package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatistiqueRepository extends JpaRepository<Statistique, Integer> {
}
