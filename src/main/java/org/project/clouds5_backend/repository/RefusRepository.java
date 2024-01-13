package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Refus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefusRepository extends JpaRepository<Refus, String> {

    @Query(value = "select 'RFS'||nextval('seq_refus') as id;", nativeQuery = true)
    String getNextValSequence();
}
