package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByEtatNot(Integer etat);

    @Query(value = "SELECT * FROM Place p WHERE CAST(p.valeur AS VARCHAR) LIKE %:inserer%", nativeQuery = true)
    List<Place> findSuggestion(@Param("inserer") String inserer);
    Place findByIdPlaceAndEtatNot(Integer id, Integer etat);
    Place findByValeur(int valeur);

}
