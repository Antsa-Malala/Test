package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Pourcentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PourcentageRepository extends JpaRepository<Pourcentage, String> {

    @Query(value = "SELECT valeur FROM pourcentage WHERE date_pourcentage = (SELECT MAX(date_pourcentage) FROM pourcentage) ORDER BY id_pourcentage desc LIMIT 1;", nativeQuery = true)
    Double getValeur();
}
