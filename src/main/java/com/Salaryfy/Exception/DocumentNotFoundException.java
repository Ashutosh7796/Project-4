package com.Salaryfy.Exception;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
