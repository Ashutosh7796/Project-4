package com.Salaryfy.Services;

import com.Salaryfy.Dto.QuestionDTO;
import com.Salaryfy.Entity.ExamQuestion;
import com.Salaryfy.Exception.SomethingWentWrong;
import com.Salaryfy.Interfaces.QuestionService;
import com.Salaryfy.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public void postQuestions(QuestionDTO questionDTO) {

        if (questionDTO.getQuestion()!= null) {
            ExamQuestion question = new ExamQuestion(questionDTO);
            questionRepo.save(question);
        }else {
            throw new SomethingWentWrong("Some thing went wrong");
        }
    }

    @Override
    public void updateQuestion(QuestionDTO questionDTO) {
        ExamQuestion optionalExamQuestion = questionRepo.findByQuestionId(questionDTO.getId());
        ExamQuestion examQuestion= new ExamQuestion();
        if (questionDTO!=null) {
            if (questionDTO.getQuestion() != null) {
                optionalExamQuestion.setQuestion(questionDTO.getQuestion());
            }
            if (questionDTO.getOp1() != null) {
                optionalExamQuestion.setOp1(questionDTO.getOp1());
            }
            if (questionDTO.getOp2() != null) {
                optionalExamQuestion.setOp2(questionDTO.getOp2());
            }
            if (questionDTO.getOp3() != null) {
                optionalExamQuestion.setOp3(questionDTO.getOp3());
            }
            if (questionDTO.getOp4() != null) {
                optionalExamQuestion.setOp4(questionDTO.getOp4());
            }
            if (questionDTO.getAns() != null) {
                optionalExamQuestion.setAns(questionDTO.getAns());
            }
            if (questionDTO.getSub() != null) {
                optionalExamQuestion.setSub(questionDTO.getSub());
            }
            if (questionDTO.getSetNo() != null) {
                optionalExamQuestion.setSetNo(questionDTO.getSetNo());
            }
            questionRepo.save(optionalExamQuestion);
        }else {
            throw new SomethingWentWrong("Something went wrong");
        }
    }
}
