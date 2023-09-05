package com.Salaryfy.Repository;

import com.Salaryfy.Entity.EduSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduSuggestRepo extends JpaRepository<EduSuggestion, Integer > {

    @Query("SELECT e FROM EduSuggestion e WHERE e.education = :userInput01 AND e.boardUniversity LIKE CONCAT('%', :userInput, '%')")
    public List<EduSuggestion> findByBoardUniversityContainingOrEducationContaining(
            @Param("userInput") String UserInput,
            @Param("userInput01") String education
    );
}
