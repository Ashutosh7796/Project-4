package com.Salaryfy.Exception;

import org.springframework.http.HttpStatus;

public class PlanNotFoundException extends RuntimeException{
    private HttpStatus httpStatus;

    public PlanNotFoundException(String message) {
        super(message);
    }

    public PlanNotFoundException(String message, HttpStatus httpStatus) {

        super(message);
        this.httpStatus = httpStatus;
    }

}
