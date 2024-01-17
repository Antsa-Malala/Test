package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Favoris;
import org.project.clouds5_backend.model.Boite;
import org.project.clouds5_backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface FavorisRepository extends JpaRepository<Favoris, String> {

    @Query(value = "select 'FAV'||nextval('s_favoris') as id;", nativeQuery = true)
    String getNextValSequence();

    List<Favoris> findByUtilisateur(Utilisateur utilisateur);
}
