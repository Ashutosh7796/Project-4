package com.Salaryfy.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InterviewScheduleNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;
    public InterviewScheduleNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus= httpStatus;
    }
}