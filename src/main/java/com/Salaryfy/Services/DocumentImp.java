package com.Salaryfy.Services;

import com.Salaryfy.Dto.DocumentDto;
import com.Salaryfy.Entity.Document;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IDocument;
import com.Salaryfy.Repository.DocumentRepo;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        document.setUserUser(userDetails.get());
        documentRepo.save(document);
        return "Document uploaded successfully";


    }
}
