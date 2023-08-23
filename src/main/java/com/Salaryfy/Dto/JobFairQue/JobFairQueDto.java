package com.Salaryfy.Dto.JobFairQue;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class JobFairQueDto {

    public String question;

    public String questionType;

    public String setNo;
    public Integer jobId;
}
