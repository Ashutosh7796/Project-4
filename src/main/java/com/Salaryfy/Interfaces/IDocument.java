package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.DocumentDto;
import com.Salaryfy.Entity.Document;

import java.util.List;

public interface IDocument {
    public String addDocument(DocumentDto documentDto);

    public List<Document> getAllDocument(Integer userId, String DocumentType);

    public List<Document> getByUserId(Integer userId);
}
