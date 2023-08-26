package com.Salaryfy.Dto.JobfairQ2ans;

import lombok.Data;

@Data
public class ResponseOfAllJobFair2Ans {
    private String status;
    private String response;

    public ResponseOfAllJobFair2Ans(String status) {
        this.status = status;
    }
}
