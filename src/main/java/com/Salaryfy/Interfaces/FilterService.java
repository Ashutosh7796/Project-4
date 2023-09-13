package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Filter.jobSuggest;
import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseJobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilterService {
    public List<JobDto> searchByFilter(FilterDto filterDto);

    public Page<JobDto> suggestJob(jobSuggest filterDto, int pageNo, int pageSize);

    List<SearchSuggestionDTO> getSuggestions(String query);

    public List<JobDto> searchBarFilter(String searchBarInput);

    public List<JobDto> searchBarFilter(String searchBarInput, String sortDirection, String sortField) ;

    public List<JobDto> searchByFilterAndSort(FilterDto filterDto, String sortField, String sortDirection);

}
