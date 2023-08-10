package com.Salaryfy.Exception;

public class ProfileLevelIdNotFoundException extends RuntimeException{
    public ProfileLevelIdNotFoundException(String message) {
        super(message);
    }
}
