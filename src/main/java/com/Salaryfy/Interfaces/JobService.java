package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Job.JobDto;

public interface JobService {
    public String AddJob(JobDto jobDto);

    public String EditJob(JobDto jobDto,Integer JobId);

    public  String DeleteJob(Integer JobId);
}
