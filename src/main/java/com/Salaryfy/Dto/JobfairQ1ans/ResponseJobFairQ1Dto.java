package com.Salaryfy.Dto.JobfairQ1ans;

import com.Salaryfy.Entity.JobfairQ1ans;
import lombok.Data;

@Data
public class ResponseJobFairQ1Dto {
    private String status;
    private JobfairQ1ans response;
    private String exception;

    public ResponseJobFairQ1Dto(String status) {
        this.status = status;
    }
}
