package com.Salaryfy.Exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String s) {
    }

    public UserAlreadyExistException(String s, String usernameAlreadyExists) {
    }
}
