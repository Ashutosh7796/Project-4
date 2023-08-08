package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Entity.JobfairQ1ans;
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
    public JobfairQ1ans getQ1ans(int JobFairQ1Id) {

        Optional<JobfairQ1ans> jobfairQ1ans = jobfairQ1ansRepo.findById(JobFairQ1Id);
        return jobfairQ1ans.get();
    }

    @Override
    public List<JobfairQ1ans> getallQ1ans() {


        return (List<com.Salaryfy.Entity.JobfairQ1ans>) jobfairQ1ansRepo.findAll();
    }

    @Override
    public String deleteQ1ans(int jobFairQ1Id) {
        jobfairQ1ansRepo.deleteById(jobFairQ1Id);
        return "deleted";
    }


    @Override
    public String deleteallQ1ans() {
        jobfairQ1ansRepo.deleteAll();
        return "All data deleted";
    }

    @Override
    public String updateQ1ans(int jobFairQ1Id) {

        Optional<JobfairQ1ans> optionalQ1Answer = jobfairQ1ansRepo.findById(jobFairQ1Id);
        if (optionalQ1Answer.isPresent()) {
            optionalQ1Answer.get().setAns("newAnswer");
            jobfairQ1ansRepo.save(optionalQ1Answer.get());

            return "updated";
        } else {
            throw new IllegalArgumentException("Q1 Answer not found with id: " + jobFairQ1Id);

        }

    }
}









