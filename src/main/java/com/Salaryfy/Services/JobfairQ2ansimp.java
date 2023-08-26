package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseOfAllJobFairQue;
import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
import com.Salaryfy.Dto.JobfairQ2ans.ResponseOfAllJobFair2Ans;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.JobfairQ2ans;
import com.Salaryfy.Entity.JobfairQue;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.JobFairQueOneException;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IJobfairQ2ans;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.JobfairQ2ansRepo;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobfairQ2ansimp implements IJobfairQ2ans {


    private final JobfairQ2ansRepo jobfairQ2ansRepo;
    private final JobRepository jobRepository;

    private final UserRepository userRepository;
    @Override
    public String addQ2ans(JobfairQ2ansDto jobfairQ2ansDto)
    {
        Optional<Job> job = jobRepository.findById(jobfairQ2ansDto.jobId);
        if(job.isEmpty()){throw new JobNotFoundException("job not found by id");}
        Optional<User> user = userRepository.findById(jobfairQ2ansDto.userId);
        if(job.isEmpty()){throw new UserNotFoundException("user not found by id");}

        JobfairQ2ans jobfairQ2ans = new JobfairQ2ans();
        jobfairQ2ans.setQuestion(jobfairQ2ansDto.question);
        jobfairQ2ans.setAns(jobfairQ2ansDto.ans);
        jobfairQ2ans.setJobId(jobfairQ2ansDto.jobId);
        jobfairQ2ans.setUserId(jobfairQ2ansDto.userId);
        jobfairQ2ans.setQuestionType(jobfairQ2ans.getQuestionType());
        jobfairQ2ans.setJobFairQ1AnsId(jobfairQ2ansDto.jobFairQ1Id); ;
        jobfairQ2ans.setQuestionType(jobfairQ2ans.getQuestion());

        jobfairQ2ansRepo.save(jobfairQ2ans);
        return "Data added";
    }

    @Override
    public JobfairQ2ans getQ2ans(int jobFairQ1Id) {

        Optional<JobfairQ2ans> jobfairQ2ans = jobfairQ2ansRepo.findById(jobFairQ1Id);
        if(jobfairQ2ans.isEmpty()){throw new JobFairQueOneException("Job Fair Question not by Id");
        }

        return jobfairQ2ans.get();
    }

    @Override
    public List<JobfairQ2ans> getallQ2ans() {
        return (List<JobfairQ2ans>)jobfairQ2ansRepo.findAll() ;
    }

    @Override
    public String deleteQ2ans(int jobFairQ1Id) {
        Optional<JobfairQ2ans> jobfairQ1ans = jobfairQ2ansRepo.findById(jobFairQ1Id);
        if(jobfairQ1ans.isEmpty()){throw new JobFairQueOneException("Job Fair Question not by Id");
        }
        jobfairQ2ansRepo.deleteById(jobFairQ1Id);
        return "Data Deleted";
    }

    @Override
    public String deleteallQ2ans() {
         jobfairQ2ansRepo.deleteAll();
         return "All data deleted";
    }

    @Override
    public JobfairQ2ans getJobFairDetailsByUserId(Integer userId) {
        Optional<JobfairQ2ans> jobfairQ2ans = jobfairQ2ansRepo.findByUserId(userId);
        if(jobfairQ2ans.isEmpty()){
            throw new UserNotFoundException("user not found by id");
        }
        return jobfairQ2ans.get();


    }
    @Override
    public String addAllJobFairAns(List<JobfairQ2ansDto> listOfjobFairQueDto) {
        String exceptionJobIds = "invalid id ";
        boolean flag = false;
        List<JobfairQ2ans> listOfJobFairQ2 = new ArrayList<>();
        System.out.println("185");

        for(int counter = 0; counter<listOfjobFairQueDto.size();counter++){
//            Optional<Job> job = jobRepository.findById(listOfjobFairQueDto.get(counter).getJobId());
//            if(job.isEmpty()){
//                System.err.println("*");
////                exceptionJobIds = exceptionJobIds+": "+listOfjobFairQueDto.get(counter).getJobId();
//            }
//            else {
                System.err.println(counter);

                JobfairQ2ans jobfairQ2ans = new JobfairQ2ans();
                jobfairQ2ans.setQuestion(listOfjobFairQueDto.get(counter).question);
                jobfairQ2ans.setAns(listOfjobFairQueDto.get(counter).ans);
                jobfairQ2ans.setJobId(listOfjobFairQueDto.get(counter).jobId);
                jobfairQ2ans.setUserId(listOfjobFairQueDto.get(counter).userId);
                jobfairQ2ans.setQuestionType(jobfairQ2ans.getQuestionType());
                jobfairQ2ans.setJobFairQ1AnsId(listOfjobFairQueDto.get(counter).jobFairQ1Id);
            listOfJobFairQ2.add(jobfairQ2ans);
//            }
        }
        System.out.println("194");
        System.err.println(exceptionJobIds);
        jobfairQ2ansRepo.saveAll(listOfJobFairQ2);
        System.out.println("197");

//        if(flag){
//            responseOfAllJobFairQue.("!!!!! Success but "+exceptionJobIds);
//        }
//        responseOfAllJobFairQue.setResponse("job fair question added ");
        return "job fair question added";
    }


}
