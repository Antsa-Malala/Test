package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Message;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Document(collection = "messaging")
public interface MessageRepository extends MongoRepository<Message, String> {
//    @Query("{ $or: [ { 'idUtilisateur1': ?0, 'idUtilisateur2': ?1 }, { 'idUtilisateur1': ?1, 'idUtilisateur2': ?0 } ] }")
//    List<Message> getMessageBetweenOrderByDateMessageDesc(String iduser1, String iduser2);

    @Query(value = "{ $or: [ { 'idUtilisateur1': ?0, 'idUtilisateur2': ?1 }, { 'idUtilisateur1': ?1, 'idUtilisateur2': ?0 } ] }", sort = "{ 'dateMessage': -1 }")
//@Query(value = "{ 'idUtilisateur1': ?0, 'idUtilisateur2': ?1 }")
    List<Message> getMessageUsers(String iduser1, String iduser2);

}
