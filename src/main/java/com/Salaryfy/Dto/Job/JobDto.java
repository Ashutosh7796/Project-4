package com.Salaryfy.Dto.Job;

import com.Salaryfy.Entity.Experiencedoc;
import com.Salaryfy.Entity.Job;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Builder
public class JobDto {

    private Integer JobId;

    private String postName;

    private String JobType;

    private String companyName;

    private String location;

    private String logo;

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

    private List<String> interviewLocation;

    private  String interviewTimeSlot1Min;

    private  String interviewTimeSlot1Max;

    private  String interviewTimeSlot2Min;

    private  String interviewTimeSlot2Max;

    public JobDto(Job job) {
        this.postName = job.getPostName();
        this.companyName = job.getCompanyName();
        this.location = job.getLocation();
        this.noOfPosts = job.getNoOfPosts();
        this.interviewStartDate = job.getInterviewStartDate();
        this.interviewEndDate = job.getInterviewEndDate();
        this.essentialRequirements = job.getEssentialRequirements();
        this.incentives = job.getIncentives();
        this.logo=job.getLogo();
        this.interviewDetails = job.getInterviewDetails();
        this.jobDetails = job.getJobDetails();
        this.date = job.getDate();
        this.JobType = job.getJobType();
        this.startingSalary = job.getStartingSalary();
        this.jobFairSetNo = job.getJobFairSetNo();
        this.status = job.getStatus();
        this.User_Id = job.getUserUser().getUser_id();
        this.JobId =job.getJobId();
        this.interviewTimeSlot1Min=job.getInterviewTimeSlot1Min();
        this.interviewTimeSlot1Max =job.getInterviewTimeSlot1Max();
        this.interviewTimeSlot2Min=job.getInterviewTimeSlot2Min();
        this.interviewTimeSlot2Max =job.getInterviewTimeSlot2Max();
        this.interviewLocation = job.getInterviewLocation();

    }

    public JobDto(Experiencedoc experiencedoc) {
    }
}
