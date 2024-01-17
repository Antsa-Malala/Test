package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Modele;
import org.project.clouds5_backend.model.Categorie;
import org.project.clouds5_backend.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ModelRepository extends JpaRepository<Modele, Integer> {
    List<Modele> findByEtatNot(Integer etat);
    List<Modele> findByCategorie(Categorie categorie);

    @Query("SELECT m FROM Modele m WHERE m.categorie.idCategorie = :idCategorie AND m.marque.idMarque = :idMarque")
    List<Modele> getByCategorieMarque(@Param("idCategorie") Integer idCategorie, @Param("idMarque") Integer idMarque);

    Modele findByIdModeleAndEtatNot(Integer id, Integer etat);
    List<Modele> findByMarque(Marque marque);
}
