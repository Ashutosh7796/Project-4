package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseJobDto;

import java.util.List;

public interface FilterService {
    public List<JobDto> searchByFilter(FilterDto filterDto);

    public ResponseJobDto searchBarFilter(String searchBarInput, Integer pageNo, ResponseJobDto responseJobDto) ;
}
