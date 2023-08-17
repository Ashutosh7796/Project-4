package com.Salaryfy.Exception;

import org.springframework.http.HttpStatus;

public class PgProgramNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;

//    public PgProgramNotFoundException(String message) {
//        super(message);
//    }

    public PgProgramNotFoundException(String message, HttpStatus httpStatus) {

        super(message);
        this.httpStatus = httpStatus;
    }

    public PgProgramNotFoundException(String message) {
   super(message);
    }
}
