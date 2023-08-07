package com.Salaryfy.Entity;

import com.Salaryfy.Dto.Job.JobDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobId", nullable = false)
    private Integer jobId;

    @Column(name = "PostName", length = 100)
    private String postName;


    @Column(name = "CompanyName", length = 250)
    private String companyName;

    @Column(name = "Location", length = 45)
    private String location;

    @Column(name = "NoOfPosts")
    private Integer noOfPosts;

    @Column(name = "InterviewStartDate")
    private LocalDate interviewStartDate;

    @Column(name = "InterviewEndDate")
    private LocalDate interviewEndDate;

    @Lob
    @Column(name = "EssentialRequirements", length = 500)
    private String essentialRequirements;

    @Column(name = "Incentives", length = 300)
    private String incentives;

    @Lob
    @Column(name = "InterviewDetails", length = 500)
    private String interviewDetails;

    @Lob
    @Column(name = "JobDetails", length = 500)
    private String jobDetails;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "StartingSalary", length = 45)
    private String startingSalary;

    @Column(name = "jobFairSetNo")
    private Integer jobFairSetNo;

    @Column(name = "Status")
    private Boolean status;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userId", nullable = false)
    private User userUser;

    @OneToMany(mappedBy = "jobsJob")
    private List<PlacementDetails> bookings = new LinkedList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_interview", joinColumns = @JoinColumn(name = "JobId", referencedColumnName = "JobId"),
            inverseJoinColumns = @JoinColumn(name = "InterviewScheduleId", referencedColumnName = "InterviewScheduleId"))
    private List<InterviewSchedule> interviewSchedule;

    public Job(JobDto jobDto) {
        this.postName = jobDto.getPostName();
        this.companyName = jobDto.getCompanyName();
        this.location = jobDto.getLocation();
        this.noOfPosts = jobDto.getNoOfPosts();
        this.interviewStartDate = jobDto.getInterviewStartDate();
        this.interviewEndDate = jobDto.getInterviewEndDate();
        this.essentialRequirements = jobDto.getEssentialRequirements();
        this.incentives = jobDto.getIncentives();
        this.interviewDetails = jobDto.getInterviewDetails();
        this.jobDetails = jobDto.getJobDetails();
        this.date = jobDto.getDate();
        this.startingSalary = jobDto.getStartingSalary();
        this.jobFairSetNo = jobDto.getJobFairSetNo();
        this.status = jobDto.getStatus();
    }
}

