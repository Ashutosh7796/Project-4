package com.Salaryfy.Dto.Job;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSingleJobDto {
    private String message;
    private Object object;
    private String exception;

    public ResponseSingleJobDto(String message)
    {
        this.message = message;
    }

}

