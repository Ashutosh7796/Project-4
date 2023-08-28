package com.Salaryfy.Entity;

import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
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


    @Column(name = "Question", length = 250)
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
    private Integer jobFairQ1Id;

    public JobfairQ2ans() {
    }

    public JobfairQ2ans(JobfairQ2ansDto jobfairQ2ansDto) {
        this.question = jobfairQ2ansDto.question;
        this.ans = jobfairQ2ansDto.ans;
        this.userId = jobfairQ2ansDto.userId;
        this.jobId = jobfairQ2ansDto.jobId;
        this.questionType = jobfairQ2ansDto.questionType;
        this.jobFairQ1Id = jobfairQ2ansDto.jobFairQ1Id;
    }
}