package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId", nullable = false)
    private Integer questionId;

    @Column(name = "Question", length = 250)
    private String Question;

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

}