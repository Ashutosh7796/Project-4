package com.Salaryfy.Dto.Job;

import com.Salaryfy.Entity.JobfairQue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
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
