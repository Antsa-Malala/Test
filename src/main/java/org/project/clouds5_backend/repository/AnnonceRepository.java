package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.project.clouds5_backend.model.Utilisateur;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, String> {
    List<Annonce> findByEtatNot(Integer etat);

    Annonce findByIdAnnonceAndEtatNot(String id, Integer etat);
    @Query(value = "select 'ANN'||nextval('seq_annonce') as id;", nativeQuery = true)
    String getNextValSequence();

    List<Annonce> findByEtat(Integer etat);

    List<Annonce> findByUtilisateurOrderByDateAnnonceDesc(Utilisateur utilisateur);
}
