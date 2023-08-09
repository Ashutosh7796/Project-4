package com.Salaryfy.Exception;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String pageNotFound) {

            super(pageNotFound);
        }

}



