package com.Salaryfy.Dto;

import com.Salaryfy.Entity.JobfairQue;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    public String status;
    public List<JobfairQue> response;

    public ResponseDto(String status) {
        this.status = status;
    }
}
