package com.Salaryfy.Controller;

import com.Salaryfy.Dto.QuestionDTO;
import com.Salaryfy.Exception.SomethingWentWrong;
import com.Salaryfy.Interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/question")
@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/postQuestions")
    public ResponseEntity<?> postQuestions(@RequestBody QuestionDTO questionDTO){
        try {
            questionService.postQuestions(questionDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Successful");
        }catch (SomethingWentWrong e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }

    @PatchMapping("/updateQuestion")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO questionDTO){
        try {
            questionService.updateQuestion(questionDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Successful");
        }catch (SomethingWentWrong e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }

}
