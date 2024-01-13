package org.project.clouds5_backend.repository;

import java.util.Optional;
import java.util.List;

import org.project.clouds5_backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByMail(String email);

    List<Utilisateur> findByEtatNot(Integer etat);
    Utilisateur findByIdUtilisateurAndEtatNot(String id, Integer etat);

    @Query(value = "select 'USR'||nextval('seq_utilisateur') as id;", nativeQuery = true)
    String getNextValSequence();
}
