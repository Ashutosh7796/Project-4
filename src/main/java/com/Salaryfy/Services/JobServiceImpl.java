package com.Salaryfy.Services;

import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.JobService;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;
    @Override
    public String AddJob(JobDto jobDto) {
        User user=userRepository.findById(jobDto.getUser_Id()).orElseThrow(()->new UserNotFoundException("User Not found", HttpStatus.NOT_FOUND));
        Job job= new Job(jobDto);
        job.setUserUser(user);
        jobRepository.save(job);
        return "Job Added";
    }

    @Override
    public String EditJob(JobDto jobDto, Integer JobId) {
        return null;
    }

    @Override
    public List<JobDto> getAlljobsWithPages(int PageNo) {
        List<Job> listOfJob =jobRepository.findAll();
        if((PageNo*10)>listOfJob.size()-1)
        {
            throw new PageNotFoundException("page not found");
        }
        if(listOfJob.size()<=0) {throw new JobNotFoundException("Job not found",HttpStatus.NOT_FOUND);
        }
        List<JobDto> listOfjobDto = new ArrayList<>();

        int pageStart=PageNo*10;
        int pageEnd=pageStart+10;
        int diff=(listOfJob.size()) - pageStart;
        for(int counter=pageStart,i=1;counter<pageEnd;counter++,i++){
            if(pageStart>listOfJob.size()){break;}


            JobDto jobDto = new JobDto(listOfJob.get(counter));
            jobDto.setJobId(listOfJob.get(counter).getJobId());
            listOfjobDto.add(jobDto);


            if(diff == i){
                break;
            }
        }

        return listOfjobDto;
    }

    @Override
    public JobDto findById(Integer JobId)
    {
        Optional<Job> job = jobRepository.findById(JobId);
        if (job.isEmpty())
        {
            throw new JobNotFoundException("Job not found",HttpStatus.NOT_FOUND);
        }
        JobDto jobDto = new JobDto(job.get());
        jobDto.setJobId(JobId);
        return jobDto;
    }
}
