package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
    @Query("SELECT jfq FROM Document jfq WHERE jfq.documentType = :documentType")
    public List<Document> findByDocumentType(String documentType);
}
