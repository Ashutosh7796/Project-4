package com.Salaryfy.Dto.JobfairQ1ans;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobfairQ1ansDto {

    public Integer JobfairQ1Id;
    public String question;
    public String ans;
    public Integer userId;
    public Integer jobId;
}
