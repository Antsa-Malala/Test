package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorisRepository extends JpaRepository<Favoris, String> {
}
