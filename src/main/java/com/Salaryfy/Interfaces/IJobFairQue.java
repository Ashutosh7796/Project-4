package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Entity.JobfairQue;

import java.util.List;

public interface IJobFairQue {
    public String addJobFairQuestion(JobFairQueDto jobFairQueDto);
    public ResponseJobFairQueDto getAllJobFairDetails(Integer pageNo, ResponseJobFairQueDto responseJobFairQ1Dto) ;

    public JobfairQue getJobFairDetails(Integer jobFairQueId);

    public List<JobfairQue> getJobFairDetailsBySetNo(String setNo, Integer pageNo);

    public List<JobfairQue> getJobFairDetailsBySetNoAndQueType(String setNo,Boolean questionType, Integer pageNo);
}
