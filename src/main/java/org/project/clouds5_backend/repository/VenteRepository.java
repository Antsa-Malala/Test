package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VenteRepository extends JpaRepository<Vente, String> {
    @Query(value = "select 'VNT'||nextval('seq_vente') as id;", nativeQuery = true)
    String getNextValSequence();
}
