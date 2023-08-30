package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseOfAllJobFairQue;
import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
import com.Salaryfy.Dto.JobfairQ2ans.ResponseOfAllJobFair2Ans;
import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQ2ans;

import java.util.List;

public interface IJobfairQ2ans {

    public String addQ2ans(JobfairQ2ansDto jobfairQ2ansDto);

    public JobfairQ2ans getQ2ans(int jobFairQ1Id);

    public List<JobfairQ2ans> getallQ2ans();


    public String deleteQ2ans(int jobFairQ1Id);

    public String deleteallQ2ans();

    public JobfairQ2ans getJobFairDetailsByUserId(Integer userId);

    public String addAllJobFairAns(List<JobfairQ2ansDto> listOfjobFairQueDto);

    public List<JobfairQ2ans> getByUserIdAndJobId(Integer userId, Integer jobId);

    public String UpdateQ2AnsByUserIdAndJobId(List<JobfairQ2ansDto> jobfairQ2ansDto);
}
