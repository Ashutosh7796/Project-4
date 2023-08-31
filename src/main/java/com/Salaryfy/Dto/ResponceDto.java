package com.Salaryfy.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponceDto {
    public String status;
    public String message;

    public ResponceDto(String status, String message) {
        this.status = status;
        this.message = message;

    }
}
