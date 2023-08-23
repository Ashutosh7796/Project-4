package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseJobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;

import java.util.List;

public interface FilterService {
    public List<JobDto> searchByFilter(FilterDto filterDto);

    List<SearchSuggestionDTO> getSuggestions(String query);

    public List<JobDto> searchBarFilter(String searchBarInput) ;

    public List<JobDto> searchByFilterAndSort(FilterDto filterDto, String sortField, String sortDirection);

}
