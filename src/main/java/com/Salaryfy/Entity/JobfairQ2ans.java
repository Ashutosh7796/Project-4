package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jobfairq2ans")
public class JobfairQ2ans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobFairQ1AnsId", nullable = false)
    private Integer JobFairQ1AnsId;

    @Column(name = "Question", length = 45)
    private String question;

    @Column(name = "Ans", length = 200)
    private String ans;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "JobId")
    private Integer jobId;
    @Column(name = "QuestionType")
    private String questionType;

    @Column(name = "JobFairQueId")
    private Integer jobFairQuestionId;

}