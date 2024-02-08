package org.project.clouds5_backend.repository;

import org.project.clouds5_backend.model.Mobile;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Document(collection = "mobileToken")
public interface MobileRepository extends MongoRepository<Mobile, String> {

}
