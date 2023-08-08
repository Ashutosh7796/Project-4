package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam_sol")
public class ExamSol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examId", nullable = false)
    private Integer examId;

    @Column(name = "Question", length = 250)
    private String question;

    @Column(name = "Ans", length = 250)
    private String ans;

    @Column(name = "userAns", length = 75)
    private String userAns;

    @Column(name = "userId")
    private Integer userId;

}