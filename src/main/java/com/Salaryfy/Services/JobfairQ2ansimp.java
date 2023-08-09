package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
import com.Salaryfy.Entity.JobfairQ2ans;
import com.Salaryfy.Interfaces.IJobfairQ2ans;
import com.Salaryfy.Repository.JobfairQ2ansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobfairQ2ansimp implements IJobfairQ2ans {

    @Autowired
    private JobfairQ2ansRepo jobfairQ2ansRepo;

    @Override
    public String addQ2ans(JobfairQ2ansDto jobfairQ2ansDto)
    {
        JobfairQ2ans jobfairQ2ans = new JobfairQ2ans();
        jobfairQ2ans.setQuestion(jobfairQ2ansDto.question);
        jobfairQ2ans.setAns(jobfairQ2ansDto.ans);
        jobfairQ2ans.setJobId(jobfairQ2ansDto.jobId);
        jobfairQ2ans.setUserId(jobfairQ2ansDto.userId);

        jobfairQ2ansRepo.save(jobfairQ2ans);
        return "Data added";
    }

    @Override
    public JobfairQ2ans getQ2ans(int jobFairQ1Id) {

        Optional<JobfairQ2ans> jobfairQ2ans = jobfairQ2ansRepo.findById(jobFairQ1Id);
        return jobfairQ2ans.get();
    }

    @Override
    public List<JobfairQ2ans> getallQ2ans() {
        return (List<JobfairQ2ans>)jobfairQ2ansRepo.findAll() ;
    }

    @Override
    public String deleteQ2ans(int jobFairQ1Id) {
        jobfairQ2ansRepo.deleteById(jobFairQ1Id);
        return "Data Deleted";
    }

    @Override
    public String deleteallQ2ans() {
         jobfairQ2ansRepo.deleteAll();
         return "All data deleted";
    }




}
