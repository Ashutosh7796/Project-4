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
import lombok.RequiredArgsConstructor;
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
    private static final String CLEARBIT_LOGO_API = "https://logo.clearbit.com/";


    @Override
    public String AddJob(JobDto jobDto) {
        String logo = CLEARBIT_LOGO_API + jobDto.getLogo().toLowerCase().replace(" ", "") + ".com";

        User user = userRepository.findById(jobDto.getUser_Id()).orElseThrow(() -> new UserNotFoundException("User Not found", HttpStatus.NOT_FOUND));
        Job job = new Job(jobDto,logo);
        job.setUserUser(user);
        jobRepository.save(job);
        return "Job Added";
    }

    @Override
    public String EditJob(JobDto jobDto, Integer JobId) {
       Job job =jobRepository.findById(JobId).orElseThrow(()->new JobNotFoundException(("Job not found"),HttpStatus.NOT_FOUND));

       job.setPostName(jobDto.getPostName());
       job.setCompanyName(jobDto.getCompanyName());
       job.setLocation(jobDto.getLocation());
       job.setNoOfPosts(jobDto.getNoOfPosts());
       job.setInterviewStartDate(jobDto.getInterviewStartDate());
       job.setInterviewEndDate(jobDto.getInterviewEndDate());
       job.setIncentives(jobDto.getIncentives());
       job.setJobDetails(jobDto.getJobDetails());
       job.setDate(jobDto.getDate());
       job.setStartingSalary(jobDto.getStartingSalary());
       job.setJobFairSetNo(jobDto.getJobFairSetNo());
       job.setStatus(jobDto.getStatus());
       job.setInterviewDetails(jobDto.getInterviewDetails());
       job.setEssentialRequirements(jobDto.getEssentialRequirements());
       jobRepository.save(job);
        return "Job Updated"+JobId;
    }

    @Override
    public String updateJobFields(JobDto jobDto, Integer JobId) {
        Job job = jobRepository.findById(JobId).orElseThrow(() -> new JobNotFoundException("Job not found", HttpStatus.NOT_FOUND));

        if (jobDto.getPostName() != null) {
            job.setPostName(jobDto.getPostName());
        }
        if (jobDto.getCompanyName() != null) {
            job.setCompanyName(jobDto.getCompanyName());
        }
        if (jobDto.getLocation() != null) {
            job.setLocation(jobDto.getLocation());
        }
        if (jobDto.getNoOfPosts() != null) {
            job.setNoOfPosts(jobDto.getNoOfPosts());
        }
        if (jobDto.getInterviewStartDate() != null) {
            job.setInterviewStartDate(jobDto.getInterviewStartDate());
        }
        if(jobDto.getJobType()!=null){
            job.setJobType(jobDto.getJobType());

        }
        if (jobDto.getInterviewEndDate() != null) {
            job.setInterviewEndDate(jobDto.getInterviewEndDate());
        }
        if (jobDto.getEssentialRequirements() != null) {
            job.setEssentialRequirements(jobDto.getEssentialRequirements());
        }
        if (jobDto.getIncentives() != null) {
            job.setIncentives(jobDto.getIncentives());
        }
        if (jobDto.getInterviewDetails() != null) {
            job.setInterviewDetails(jobDto.getInterviewDetails());
        }
        if (jobDto.getJobDetails() != null) {
            job.setJobDetails(jobDto.getJobDetails());
        }
        if (jobDto.getDate() != null) {
            job.setDate(jobDto.getDate());
        }
        if (jobDto.getStartingSalary() != null) {
            job.setStartingSalary(jobDto.getStartingSalary());
        }
        if (jobDto.getJobFairSetNo() != null) {
            job.setJobFairSetNo(jobDto.getJobFairSetNo());
        }
        if (jobDto.getStatus() != null) {
            job.setStatus(jobDto.getStatus());
        }

        jobRepository.save(job);

        return "Job fields updated successfully";
    }

    @Override
    public List<JobDto> getAlljobsWithPages(int PageNo) {
        List<Job> listOfJob = jobRepository.findAll();
        if ((PageNo * 10) > listOfJob.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfJob.size() <= 0) {
            throw new JobNotFoundException("Job not found", HttpStatus.NOT_FOUND);
        }
        List<JobDto> listOfjobDto = new ArrayList<>();

        int pageStart = PageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfJob.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfJob.size()) {
                break;
            }
            JobDto jobDto = new JobDto(listOfJob.get(counter));
            jobDto.setJobId(listOfJob.get(counter).getJobId());

            listOfjobDto.add(jobDto);
            if (diff == i) {
                break;
            }
        }
        return listOfjobDto;
    }

    @Override
    public JobDto findById(Integer JobId) {
        Optional<Job> job = jobRepository.findById(JobId);
        if (job.isEmpty()) {
            throw new JobNotFoundException("Job not found", HttpStatus.NOT_FOUND);
        }
        JobDto jobDto = new JobDto(job.get());
        jobDto.setJobId(JobId);
        return jobDto;
    }

    @Override
    public List<JobDto> getJobsByStatusWithPages(int PageNo, Boolean status) {
        List<Job> listOfJobsByStatus = jobRepository.getJobsByStatus(status);
        if ((PageNo * 10) > listOfJobsByStatus.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfJobsByStatus.size() <= 0) {
            throw new JobNotFoundException("Job not found", HttpStatus.NOT_FOUND);
        }
        List<JobDto> listOfJobStatus = new ArrayList<>();
        int pageStart = PageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfJobsByStatus.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfJobsByStatus.size()) {
                break;
            }
            JobDto jobDto = new JobDto(listOfJobsByStatus.get(counter));
            jobDto.setJobId(listOfJobsByStatus.get(counter).getJobId());
            listOfJobStatus.add(jobDto);
            if (diff == i) {
                break;
            }
        }
        return listOfJobStatus;
    }
}
