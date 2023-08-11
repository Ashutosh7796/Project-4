package com.Salaryfy.Dto.JobFairQue;

import com.Salaryfy.Entity.JobfairQue;
import lombok.Data;

import java.util.List;
@Data
public class ResponseJobFairQueDto {
    private String status;
    private Integer totalItems;
    private List<JobfairQue> response;
    private Integer totalPages;
    private Integer currentPage;
    private String exception;


    public ResponseJobFairQueDto(String status) {
        this.status = status;
    }
}
