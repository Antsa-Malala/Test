package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, String> {
    List<Voiture> findByEtatNot(Integer etat);
    Voiture findByIdVoitureAndEtatNot(String id, Integer etat);

    @Query(value = "select 'VTR'||nextval('seq_voiture') as id;", nativeQuery = true)
    String getNextValSequence();
}
