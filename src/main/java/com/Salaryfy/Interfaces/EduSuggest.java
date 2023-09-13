package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.EduSuggest.EduSuggestDto;

import java.util.List;

public interface EduSuggest {

    List<EduSuggestDto> getSuggestions(String userInput, String education);

}
