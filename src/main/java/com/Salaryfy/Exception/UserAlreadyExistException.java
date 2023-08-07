package com.Salaryfy.Exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException{
    private String code;

    private String message;

}
