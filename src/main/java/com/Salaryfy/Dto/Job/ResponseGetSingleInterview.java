package com.Salaryfy.Dto.Job;

import lombok.Data;

import java.util.List;

@Data
public class ResponseGetSingleInterview {
    private String message;
    private Object object;
    private String exception;
    public ResponseGetSingleInterview(String message){
        this.message=message;
    }
}
