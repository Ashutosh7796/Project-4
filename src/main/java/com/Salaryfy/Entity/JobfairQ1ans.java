package com.Salaryfy.Entity;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jobfairq1ans")
public class JobfairQ1ans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobFairQ1Id", nullable = false)
    private Integer JobFairQ1Id;

    @Column(name = "Question", length = 45)
    private String question;

    @Column(name = "Ans")
    private String ans;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "JobId")
    private Integer jobId;


}