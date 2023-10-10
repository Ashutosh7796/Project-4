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


    @Column(name = "interviewLocation")
    private List<String> interviewLocation;

    @Column(name = "interviewTimeSlot1Min")
    private  String interviewTimeSlot1Min;

    @Column(name = "interviewTimeSlot1Max")
    private  String interviewTimeSlot1Max;

    @Column(name = "interviewTimeSlot2Min")
    private  String interviewTimeSlot2Min;

    @Column(name = "interviewTimeSlot2Max")
    private  String interviewTimeSlot2Max;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userId", nullable = false)
    private User userUser;

    @OneToMany(mappedBy = "jobsJob")
    private List<PlacementDetails> bookings = new LinkedList<>();

//
@ManyToMany
@JoinTable(
        name = "job_interview_schedule",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "interview_schedule_id"))
    private List<InterviewSchedule> interviewSchedule;
//        = new ArrayList<>();


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
        this.interviewTimeSlot1Min= jobDto.getInterviewTimeSlot1Min();
        this.interviewTimeSlot1Max = jobDto.getInterviewTimeSlot1Max();
        this.interviewTimeSlot2Min = jobDto.getInterviewTimeSlot2Min();
        this.interviewTimeSlot2Max = jobDto.getInterviewTimeSlot2Max();

        this.interviewLocation= jobDto.getInterviewLocation();
//        System.err.println(Arrays.toString(jobDto.getInterviewLocation()));


    }

}