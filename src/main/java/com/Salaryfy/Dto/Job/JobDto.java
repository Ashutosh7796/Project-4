package com.Salaryfy.Dto.Job;

import com.Salaryfy.Entity.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
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

    private Integer User_Id;

    public JobDto(Job job) {
        this.postName = job.getPostName();
        this.companyName = job.getCompanyName();
        this.location = job.getLocation();
        this.noOfPosts = job.getNoOfPosts();
        this.interviewStartDate = job.getInterviewStartDate();
        this.interviewEndDate = job.getInterviewEndDate();
        this.essentialRequirements = job.getEssentialRequirements();
        this.incentives = job.getIncentives();
        this.interviewDetails = job.getInterviewDetails();
        this.jobDetails = job.getJobDetails();
        this.date = job.getDate();
        this.startingSalary = job.getStartingSalary();
        this.jobFairSetNo = job.getJobFairSetNo();
        this.status = job.getStatus();
    }
}
