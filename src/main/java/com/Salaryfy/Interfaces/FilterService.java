package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;

import java.util.List;

public interface FilterService {
    public List<JobDto> searchByFilter(FilterDto filterDto, int PageNo);

    List<SearchSuggestionDTO> getSuggestions(String query);
}
