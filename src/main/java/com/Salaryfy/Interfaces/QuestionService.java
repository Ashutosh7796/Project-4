package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.QuestionDTO;

public interface QuestionService {
    void postQuestions(QuestionDTO questionDTO);

    void updateQuestion(QuestionDTO questionDTO);
}
