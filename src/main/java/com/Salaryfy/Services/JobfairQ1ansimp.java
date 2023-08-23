package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.JobFairQueOneException;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IJobfairQ1ans;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.JobfairQ1ansRepo;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobfairQ1ansimp implements IJobfairQ1ans {


    private final JobfairQ1ansRepo jobfairQ1ansRepo;
    private final JobRepository jobRepo;
    private final UserRepository userRepo;


    @Override
    public String addQ1ans(JobfairQ1ansDto jobfairQ1ansDto) {

        Optional<Job> job = jobRepo.findById(jobfairQ1ansDto.jobId);
        if(job.isEmpty()){throw new JobNotFoundException("job not found by id");}
        Optional<User> user = userRepo.findById(jobfairQ1ansDto.userId);
        if(job.isEmpty()){throw new UserNotFoundException("user not found by id");}

        JobfairQ1ans jobfairQ1ans = new JobfairQ1ans();
        jobfairQ1ans.setQuestion(jobfairQ1ansDto.question);
        jobfairQ1ans.setAns(jobfairQ1ansDto.ans);
        jobfairQ1ans.setJobId(jobfairQ1ansDto.jobId);
        jobfairQ1ans.setUserId(jobfairQ1ansDto.userId);

        jobfairQ1ansRepo.save(jobfairQ1ans);

        return "job fair one question added";
    }

    @Override
    public JobfairQ1ans getQ1ans(Integer JobFairQ1Id) {

        Optional<JobfairQ1ans> jobfairQ1ans = jobfairQ1ansRepo.findById(JobFairQ1Id);
        if(jobfairQ1ans.isEmpty()){throw new JobFairQueOneException("Job Fair Question not by Id");
        }
        return jobfairQ1ans.get();
    }



    @Override
    public String deleteQ1ans(Integer jobFairQ1Id) {
        Optional<JobfairQ1ans> jobfairQ1ans = jobfairQ1ansRepo.findById(jobFairQ1Id);
        if(jobfairQ1ans.isEmpty()){throw new JobFairQueOneException("Job Fair Question not by Id");
        }
        jobfairQ1ansRepo.deleteById(jobFairQ1Id);
        return "delete job fair question ans";
    }

    @Override
    public List<JobfairQ1ans> getallQ1ans(Integer pageNo) {


        return jobfairQ1ansRepo.findAll();
    }

    @Override
    public JobfairQ1ans getJobFairDetailsByUserId(Integer userId) {
        Optional<JobfairQ1ans> jobfairQ1ans = jobfairQ1ansRepo.findByUserId(userId);
        if(jobfairQ1ans.isEmpty()){
            throw new UserNotFoundException("user not found by id");
        }
        return jobfairQ1ans.get();
    }
}









