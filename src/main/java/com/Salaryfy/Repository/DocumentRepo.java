package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
    @Query("SELECT jfq FROM Document jfq WHERE jfq.userId = :userId AND jfq.documentType = :documentType")
    public List<Document> findByDocumentTypeAndUserID(Integer userId,String documentType);
    @Query("SELECT jfq FROM Document jfq WHERE jfq.userId = :userId")
    public List<Document> findByUserId(Integer userId);
}
