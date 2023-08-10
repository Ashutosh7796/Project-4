package com.Salaryfy.Dto.Job;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAllScheduleInterviews {

    private String message;
    private List<InterviewScheduleDto> list;
    private String exception;

    public ResponseAllScheduleInterviews(String message) {
        this.message = message;
    }
}
