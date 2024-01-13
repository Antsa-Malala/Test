package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValidationRepository extends JpaRepository<Validation, String> {

    @Query(value = "select 'VLD'||nextval('seq_validation') as id;", nativeQuery = true)
    String getNextValSequence();
}
