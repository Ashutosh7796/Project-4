package com.Salaryfy.Dto.Job;

import lombok.Data;

import java.util.List;

@Data
public class ResponseGetAllJobDto {
    private String message;
    private List<JobDto> list;
    private String exception;

    public ResponseGetAllJobDto(String message) {
        this.message = message;
    }
}
