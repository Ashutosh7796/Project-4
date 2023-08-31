package com.Salaryfy.Dto;

import com.Salaryfy.Entity.Document;
import lombok.Data;

@Data
public class ResponseSingleDto {
    private String status;
    private Document response;

    public ResponseSingleDto(String status) {
        this.status = status;
    }
}
