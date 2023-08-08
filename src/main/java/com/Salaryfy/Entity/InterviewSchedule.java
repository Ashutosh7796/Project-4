package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "interviewSchedule")
public class InterviewSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InterviewScheduleId", nullable = false)
    private Integer InterviewScheduleId;

    @Column(name = "location", length = 45)
    private String location;

    @Column(name = "InterviewDate")
    private LocalDate interviewDate;

    @Column(name = "Time")
    private LocalTime time;

    @Column(name = "Date")
    private Instant date;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Status", length = 45)
    private String status;



}