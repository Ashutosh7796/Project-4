package com.Salaryfy.Dto.Plan;

import lombok.Data;

@Data
public class ResponseSinglePlanDto {

    private String message;
    private Object object;
    private String exception;

    public ResponseSinglePlanDto(String message)
    {
        this.message = message;
    }

}
