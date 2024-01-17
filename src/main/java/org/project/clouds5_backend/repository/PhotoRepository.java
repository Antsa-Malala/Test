package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Photo;
import org.project.clouds5_backend.model.Porte;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Document(collection = "photo")
public interface PhotoRepository extends MongoRepository<Photo, Integer> {
    List<Photo> findByIdVoiture(String id);

}
