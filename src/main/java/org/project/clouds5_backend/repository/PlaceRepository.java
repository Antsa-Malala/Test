package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByEtatNot(Integer etat);
    Place findByIdPlaceAndEtatNot(Integer id, Integer etat);
}
