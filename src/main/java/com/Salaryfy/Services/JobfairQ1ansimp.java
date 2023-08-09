package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQ2ans;
import com.Salaryfy.Interfaces.IJobfairQ1ans;
import com.Salaryfy.Repository.JobfairQ1ansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobfairQ1ansimp implements IJobfairQ1ans {

    @Autowired
    private JobfairQ1ansRepo jobfairQ1ansRepo;


    @Override
    public String addQ1ans(JobfairQ1ansDto jobfairQ1ansDto) {

        JobfairQ1ans jobfairQ1ans = new JobfairQ1ans();
        jobfairQ1ans.setQuestion(jobfairQ1ansDto.question);
        jobfairQ1ans.setAns(jobfairQ1ansDto.ans);
        jobfairQ1ans.setJobId(jobfairQ1ansDto.jobId);
        jobfairQ1ans.setUserId(jobfairQ1ansDto.userId);

        jobfairQ1ansRepo.save(jobfairQ1ans);

        return "Data added";
    }

    @Override
    public JobfairQ1ans getQ1ans(Integer JobFairQ1Id) {

        Optional<JobfairQ1ans> jobfairQ1ans = jobfairQ1ansRepo.findById(JobFairQ1Id);
        return jobfairQ1ans.get();
    }



    @Override
    public String deleteQ1ans(Integer jobFairQ1Id) {
        jobfairQ1ansRepo.deleteById(jobFairQ1Id);
        return "deleted";
    }

    @Override
    public List<JobfairQ1ans> getallQ1ans(Integer pageNo) {


        return jobfairQ1ansRepo.findAll();
    }


}









