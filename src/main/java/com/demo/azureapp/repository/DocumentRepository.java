package com.demo.azureapp.repository;

import com.demo.azureapp.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {
}
