package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
import com.Salaryfy.Entity.JobfairQ2ans;
import com.Salaryfy.Interfaces.IJobfairQ2ans;
import com.Salaryfy.Repository.JobfairQ2ansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
