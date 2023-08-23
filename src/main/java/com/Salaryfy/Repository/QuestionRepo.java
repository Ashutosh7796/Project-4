package com.Salaryfy.Repository;

import com.Salaryfy.Entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<ExamQuestion,Integer> {
}
