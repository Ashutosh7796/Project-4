package com.Salaryfy.Repository;

import com.Salaryfy.Entity.EduSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduSuggestRepo extends JpaRepository<EduSuggestion, Integer > {
    @Query("SELECT e FROM EduSuggestion e WHERE e.boardUniversity LIKE %:userInput%")
    public List<EduSuggestion> findByBoardUniversityContaining(@Param("userInput") String userInput);

}
