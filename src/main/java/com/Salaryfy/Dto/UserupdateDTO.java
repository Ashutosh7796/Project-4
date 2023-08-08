package com.Salaryfy.Dto;


import lombok.Data;

@Data
public class UserupdateDTO {
   private String status;
    private String exception;
    private String message;

    public UserupdateDTO(String message) {
        this.status = message;
    }
}
