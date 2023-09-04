package com.Salaryfy.Entity;

import com.Salaryfy.Dto.Job.JobDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobId", nullable = false)
    private Integer jobId;

    @Column(name = "PostName", length = 100)
    private String postName;
    @Column(name = "jobType")
    private String jobType;

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

    @Column(name = "logo")
    private String logo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userId", nullable = false)
    private User userUser;

    @OneToMany(mappedBy = "jobsJob")
    private List<PlacementDetails> bookings = new LinkedList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL, CascadeType.REMOVE })
    @JoinTable(name = "user_interview", joinColumns = @JoinColumn(name = "JobId", referencedColumnName = "JobId"),
            inverseJoinColumns = @JoinColumn(name = "InterviewScheduleId", referencedColumnName = "InterviewScheduleId"))
    private List<InterviewSchedule> interviewSchedule = new ArrayList<>();


    public Job(JobDto jobDto,String logo) {
        this.postName = jobDto.getPostName();
        this.companyName = jobDto.getCompanyName();
        this.location = jobDto.getLocation();
        this.noOfPosts = jobDto.getNoOfPosts();
        this.interviewStartDate = jobDto.getInterviewStartDate();
        this.interviewEndDate = jobDto.getInterviewEndDate();
        this.essentialRequirements = jobDto.getEssentialRequirements();
        this.incentives = jobDto.getIncentives();
        this.logo=logo;
        this.interviewDetails = jobDto.getInterviewDetails();
        this.jobDetails = jobDto.getJobDetails();
        this.date = jobDto.getDate();
        this.jobType = jobDto.getJobType();
        this.startingSalary = jobDto.getStartingSalary();
        this.jobFairSetNo = jobDto.getJobFairSetNo();
        this.status = jobDto.getStatus();
    }

}