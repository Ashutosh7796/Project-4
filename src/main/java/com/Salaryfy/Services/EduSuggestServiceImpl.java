package com.Salaryfy.Services;

import com.Salaryfy.Dto.EduSuggest.EduSuggestDto;
import com.Salaryfy.Entity.EduSuggestion;
import com.Salaryfy.Interfaces.EduSuggest;
import com.Salaryfy.Repository.EduSuggestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EduSuggestServiceImpl implements EduSuggest {

    private final EduSuggestRepo eduSuggestRepo;

    public List<EduSuggestDto> getSuggestions(String userInput) {

        List<EduSuggestion> suggestions = eduSuggestRepo.findByBoardUniversityContaining(userInput);

        System.out.println("Number of suggestions retrieved: " + suggestions.size());

        return suggestions.stream()
                .map(suggestion -> {
                    EduSuggestDto dto = new EduSuggestDto();
                    dto.setBoard_University(suggestion.getBoardUniversity());
                    return dto;
                })
                .collect(Collectors.toList());

    }
}

