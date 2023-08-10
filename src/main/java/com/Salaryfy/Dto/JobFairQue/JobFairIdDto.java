package com.Salaryfy.Dto.JobFairQue;

import com.Salaryfy.Entity.JobfairQue;
import lombok.Data;

@Data
public class JobFairIdDto {
    private String status;
    private JobfairQue jobfairQue;
    private String Exception;

    public JobFairIdDto(String status) {
        this.status = status;
    }
}
