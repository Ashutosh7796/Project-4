package com.Salaryfy.Entity;

import com.Salaryfy.Dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "examQuestion")
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId", nullable = false)
    private Integer questionId;


    @Lob
    @Column(name = "question")
    private String question;

    @Column(name = "Op1", length = 45)
    private String op1;

    @Column(name = "Op2", length = 45)
    private String op2;

    @Column(name = "Op3", length = 45)
    private String op3;

    @Column(name = "Op4", length = 45)
    private String op4;

    @Column(name = "Ans", length = 45)
    private String ans;

    @Column(name = "Sub", length = 45)
    private String sub;

    @Column(name = "`Set no`", length = 45)
    private String setNo;

    public ExamQuestion(QuestionDTO questionDTO) {
        this.question=questionDTO.getQuestion();
        this.op1=questionDTO.getOp1();
        this.op2=questionDTO.getOp2();
        this.op3=questionDTO.getOp3();
        this.op4=questionDTO.getOp4();
        this.ans=questionDTO.getAns();
        this.sub=questionDTO.getSub();
        this.setNo=questionDTO.getSetNo();
    }
}