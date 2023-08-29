package com.Salaryfy.Dto.JobfairQ2ans;

import com.Salaryfy.Entity.JobfairQ2ans;
import lombok.Data;

import java.util.List;

@Data
public class ResponseAllJobFairQ2 {
    private String status;
    private List<JobfairQ2ans> response;

    public ResponseAllJobFairQ2(String status) {
        this.status = status;
    }
}
