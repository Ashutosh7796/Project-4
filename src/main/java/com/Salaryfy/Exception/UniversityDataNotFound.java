package com.Salaryfy.Exception;

import org.springframework.http.HttpStatus;

public class UniversityDataNotFound extends RuntimeException {

        private HttpStatus httpStatus;

        public UniversityDataNotFound(String message) {
            super(message);
        }

        public UniversityDataNotFound(String message, HttpStatus httpStatus) {

            super(message);
            this.httpStatus = httpStatus;
        }
    }


