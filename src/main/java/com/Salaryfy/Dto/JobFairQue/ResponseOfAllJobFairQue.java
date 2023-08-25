package com.Salaryfy.Dto.JobFairQue;

import lombok.Data;

import java.util.List;

@Data
public class ResponseOfAllJobFairQue {
    private String status;
    private String response;

    public ResponseOfAllJobFairQue(String status) {
        this.status = status;
    }
}
