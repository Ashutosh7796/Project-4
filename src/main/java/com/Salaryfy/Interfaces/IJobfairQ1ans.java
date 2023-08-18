package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Entity.JobfairQ1ans;

import java.util.List;

public interface IJobfairQ1ans {

    public String addQ1ans(JobfairQ1ansDto jobfairQ1ansDto);
    public JobfairQ1ans getQ1ans(Integer jobFairQ1Id);
    public String deleteQ1ans(Integer jobFairQ1Id);
    public List<JobfairQ1ans> getallQ1ans(Integer pageNo);

    public JobfairQ1ans getJobFairDetailsByUserId(Integer userId);


}
