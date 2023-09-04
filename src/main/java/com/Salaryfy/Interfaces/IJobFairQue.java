package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseOfAllJobFairQue;
import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQue;

import java.util.List;

public interface IJobFairQue {
    public String addJobFairQuestion(JobFairQueDto jobFairQueDto);
    public ResponseJobFairQueDto getAllJobFairDetails(Integer pageNo, ResponseJobFairQueDto responseJobFairQ1Dto) ;

    public JobfairQue getJobFairDetails(Integer jobFairQueId);

    public List<JobfairQue> getJobFairDetailsBySetNo(String setNo, Integer pageNo,ResponseJobFairQueDto responseJobFairQ1Dto);

    public List<JobfairQue> getJobFairDetailsBySetNoAndQueType(String setNo,String questionType);

    public List<JobfairQue> getJobFairDetailsByJobId(Integer jobId);

    public ResponseOfAllJobFairQue addAllJobFairQuestion(List<JobFairQueDto> listOfjobFairQueDto, ResponseOfAllJobFairQue responseOfAllJobFairQue);
//    public JobfairQ1ans getJobFairDetailsByUserId(Integer userId);
}
