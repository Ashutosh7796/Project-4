package com.Salaryfy.Dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String ans;
    private String sub;
    private String setNo;

}
