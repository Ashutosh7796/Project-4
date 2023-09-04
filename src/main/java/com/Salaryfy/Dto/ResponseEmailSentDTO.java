package com.Salaryfy.Dto;

import lombok.Data;

@Data
public class ResponseEmailSentDTO {
    String status;
    String message;

    public ResponseEmailSentDTO(String message) {
        this.message = message;
    }
}
