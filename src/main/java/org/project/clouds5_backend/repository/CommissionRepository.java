package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommissionRepository extends JpaRepository<Commission, Integer> {
    Commission findByIdCommission(Integer id);

}
