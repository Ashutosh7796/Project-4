package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public String AddJob(JobDto jobDto);

    public String EditJob(JobDto jobDto,Integer JobId);

    public String updateJobFields(JobDto jobDto,Integer JobId);

    public List<JobDto> getAlljobsWithPages(int PageNo);

    public JobDto findById(Integer JobId);

    public Optional<Job> findjobById(Integer JobId);

    public List<JobDto> getJobsByStatusWithPages(int PageNo, Boolean status);
}
