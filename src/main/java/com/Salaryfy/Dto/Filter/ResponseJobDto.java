package com.Salaryfy.Dto.Filter;

import com.Salaryfy.Dto.Job.JobDto;

import java.util.List;

public class ResponseJobDto {
    private String status;
    private Integer totalItems;
    private List<JobDto> response;
    private Integer totalPages;
    private Integer currentPage;
    private String exception;


    public ResponseJobDto(String status) {
        this.status = status;
    }


}
