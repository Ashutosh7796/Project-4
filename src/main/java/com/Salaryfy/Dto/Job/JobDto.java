package com.Salaryfy.Dto.Job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobDto {

    private Integer JobId;
    private String postName;

    private String companyName;

    private String location;

    private Integer noOfPosts;

    private LocalDate interviewStartDate;

    private LocalDate interviewEndDate;

    private String essentialRequirements;

    private String incentives;

    private String interviewDetails;

    private String jobDetails;

    private LocalDate date;

    private String startingSalary;

    private Integer jobFairSetNo;

    private Boolean status;


}
