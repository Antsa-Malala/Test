package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Mobile;
import org.project.clouds5_backend.model.Modele;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Document(collection = "mobileToken")
public interface MobileRepository extends MongoRepository<Mobile, String> {
    @Query("{'idUtilisateur': ?0, 'token': ?1}")
    Mobile findByIdUtilisateurAndToken(String idUtilisateur, String token);

    @Query("{'idUtilisateur': ?0}")
    List<Mobile> findByIdUtilisateur(String idUtilisateur);
}
