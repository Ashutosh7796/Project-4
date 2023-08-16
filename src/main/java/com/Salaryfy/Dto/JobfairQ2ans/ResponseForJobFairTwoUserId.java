package com.Salaryfy.Dto.JobfairQ2ans;

import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQ2ans;
import lombok.Data;

@Data
public class ResponseForJobFairTwoUserId {
    private String status;
    private Integer totalItems;
    private JobfairQ2ans response;
    private Integer totalPages;
    private Integer currentPage;
    private String exception;

    public ResponseForJobFairTwoUserId(String status) {
        this.status = status;
    }
}
