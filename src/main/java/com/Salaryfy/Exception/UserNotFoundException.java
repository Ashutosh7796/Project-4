package com.Salaryfy.Exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private String code;
    private String message;


    public UserNotFoundException(String userNotFound, HttpStatus httpStatus) {
    }
}
