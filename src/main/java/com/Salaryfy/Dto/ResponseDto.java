package com.Salaryfy.Dto;

import lombok.Data;

@Data
public class ResponseDto {
    public String status;
    public Object response;

    public ResponseDto(String status) {
        this.status = status;
    }
}
