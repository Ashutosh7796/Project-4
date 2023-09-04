package com.Salaryfy.Dto.Job;

import com.Salaryfy.Entity.InterviewSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScheduleDto {

    private Integer InterviewScheduleId;

    private String location;

    private String formattedTime;

    private LocalDate interviewDate;

    private LocalTime time;

    private LocalDate date;

    private Integer userId;

    private Integer jobId;


    private String status;

    public InterviewScheduleDto(InterviewSchedule interviewSchedule) {
        this.location = interviewSchedule.getLocation();
        this.interviewDate = interviewSchedule.getInterviewDate();
        this.time = interviewSchedule.getTime();
        this.date = interviewSchedule.getDate();
        this.userId = interviewSchedule.getUserId();
        this.status = interviewSchedule.getStatus();
        this.InterviewScheduleId = interviewSchedule.getInterviewScheduleId();
        this.formattedTime=interviewSchedule.getFormattedTime();

    }
}
