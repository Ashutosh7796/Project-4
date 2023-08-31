package com.Salaryfy.Dto;

import com.Salaryfy.Entity.Document;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllDocument {
    private String status;
    private List<Document> response;

    public ResponseAllDocument(String status) {
        this.status = status;
    }
}
