package com.Salaryfy.Services;

import com.Salaryfy.Dto.DocumentDto;
import com.Salaryfy.Entity.Document;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.DocumentNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IDocument;
import com.Salaryfy.Repository.DocumentRepo;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentImp implements IDocument {
    private final UserRepository userRepository;
    private final DocumentRepo documentRepo;

    @Override
    public String addDocument(DocumentDto documentDto) {
        Optional<User> userDetails = userRepository.findById(documentDto.getUserId());
        if (userDetails.isEmpty()) {
            throw new UserNotFoundException("user not found by id ");
        }
        Document document = new Document(documentDto);
        documentRepo.save(document);
        return "Document uploaded successfully";


    }

    @Override
    public List<Document> getAllDocument(Integer userId, String DocumentType) {
        List<Document> documentDetails =  documentRepo.findByDocumentTypeAndUserID(userId,DocumentType);
        if (documentDetails.isEmpty()){
            throw new DocumentNotFoundException("document not found by id");
        }
        return documentDetails;


    }

    @Override
    public List<Document> getByUserId(Integer userId) {
        List<Document> document = documentRepo.findByUserId(userId);
        if(document.isEmpty()){
            throw new DocumentNotFoundException("document not found by user id");
        }
        return document;



    }
}
