package com.Salaryfy.Entity;

import com.Salaryfy.Dto.Job.InterviewScheduleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate date;

    @Transient
    private String formattedTime;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Status", length = 45)
    private String status;

    @ManyToMany(mappedBy = "interviewSchedule", fetch = FetchType.EAGER)
    private List<Job> jobs = new ArrayList<>();

    public InterviewSchedule(InterviewScheduleDto interviewScheduleDto) {
        this.location = interviewScheduleDto.getLocation();
        this.interviewDate = interviewScheduleDto.getInterviewDate();
        this.time = interviewScheduleDto.getTime();
        this.userId=interviewScheduleDto.getUserId();
        this.date=interviewScheduleDto.getDate();
        this.status = interviewScheduleDto.getStatus();

    }
    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getFormattedTime() {
        if (time != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return time.format(formatter);
        }
        return null;
    }
}