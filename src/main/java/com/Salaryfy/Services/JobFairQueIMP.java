package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseOfAllJobFairQue;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.JobfairQue;
import com.Salaryfy.Exception.JobFairQue.JobFairQueNotFoundById;
import com.Salaryfy.Exception.JobFairQue.JobFairQuenotFoundByQueTypeAndSetNo;
import com.Salaryfy.Exception.JobFairQuestionDetailsNotFoundByJobId;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.SetNoNotFoundException;
import com.Salaryfy.Interfaces.IJobFairQue;
import com.Salaryfy.Repository.JobFairQueRepo;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class JobFairQueIMP implements IJobFairQue {
    private final JobFairQueRepo jobFairQueRepo;
    private final UserRepository userRepo;
    private final JobRepository jobRepository;

    @Override
    public String addJobFairQuestion(JobFairQueDto jobFairQueDto) {
        JobfairQue jobfairQue = new JobfairQue(jobFairQueDto);
        jobFairQueRepo.save(jobfairQue);
        return "Job Question added successfully";
    }

    @Override
    public JobfairQue getJobFairDetails(Integer jobFairQueId) {
        Optional<JobfairQue> jobfairQue = jobFairQueRepo.findById(jobFairQueId);
        if (jobfairQue.isEmpty()) {
            throw new JobFairQueNotFoundById("job details not found by id");
        }
        return jobfairQue.get();
    }

    @Override
    public ResponseJobFairQueDto getAllJobFairDetails(Integer pageNo, ResponseJobFairQueDto responseJobFairQ1Dto) {

        List<JobfairQue> jobfairQueList = jobFairQueRepo.findAll();
        if (jobfairQueList.size() <= 0) {
            throw new PageNotFoundException("Page not found");
        }

        if ((pageNo * 10) > jobfairQueList.size() - 1) {
            throw new PageNotFoundException("page not found");

        }
        responseJobFairQ1Dto.setTotalItems(jobfairQueList.size());
        Integer totalPages = jobfairQueList.size()/10;
        if(jobfairQueList.size()>totalPages){totalPages++;}
        responseJobFairQ1Dto.setTotalPages(totalPages);
        responseJobFairQ1Dto.setCurrentPage(pageNo);
//        System.out.println("list of de"+listOfCar.size());
        List<JobfairQue> listOfNewJobQue = new ArrayList<>();

        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (jobfairQueList.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > jobfairQueList.size()) {
                break;
            }


            listOfNewJobQue.add(jobfairQueList.get(counter));


            if (diff == i) {
                break;
            }
        }
         responseJobFairQ1Dto.setResponse(listOfNewJobQue);
//        System.out.println(listOfCar);
        return responseJobFairQ1Dto;
    }

    @Override
    public List<JobfairQue> getJobFairDetailsBySetNo(String setNo, Integer pageNo,ResponseJobFairQueDto responseJobFairQ1Dto) {

        List<JobfairQue> jobfairQueList = jobFairQueRepo.findBySetNo(setNo);
        if (jobfairQueList.size() <= 0) {
            throw new SetNoNotFoundException("set no not found by id");
        }
        if (jobfairQueList.size() <= 0) {
            throw new PageNotFoundException("Page not found");
        }

        if ((pageNo * 10) > jobfairQueList.size() - 1) {
            throw new PageNotFoundException("page not found");

        }
//        System.out.println("list of de"+listOfCar.size());
        List<JobfairQue> listOfNewJobQue = new ArrayList<>();

        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (jobfairQueList.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > jobfairQueList.size()) {
                break;
            }


            listOfNewJobQue.add(jobfairQueList.get(counter));


            if (diff == i) {
                break;
            }
        }

        responseJobFairQ1Dto.setTotalPages((jobfairQueList.size())/10);
        responseJobFairQ1Dto.setTotalItems(jobfairQueList.size());

//        System.out.println(listOfCar);
        return listOfNewJobQue;
    }

    @Override
    public List<JobfairQue> getJobFairDetailsBySetNoAndQueType(String setNo, Boolean questionType, Integer pageNo) {
        List<JobfairQue> jobfairQueList = jobFairQueRepo.findByQueTypeAndSetNo(questionType, setNo);
        if (jobfairQueList.size() <= 0) {
            throw new JobFairQuenotFoundByQueTypeAndSetNo("Job Fair Que not Found By Que Type And Set No");
        }

        if (jobfairQueList.size() <= 0) {
            throw new PageNotFoundException("Page not found");
        }
        if ((pageNo * 10) > jobfairQueList.size() - 1) {
            throw new PageNotFoundException("page not found");

        }
//        System.out.println("list of de"+listOfCar.size());
        List<JobfairQue> listOfNewJobQue = new ArrayList<>();

        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (jobfairQueList.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > jobfairQueList.size()) {
                break;
            }


            listOfNewJobQue.add(jobfairQueList.get(counter));


            if (diff == i) {
                break;
            }
        }

//        System.out.println(listOfCar);
        return listOfNewJobQue;

    }

    @Override
    public Object getJobFairDetailsByJobId(Integer jobId) {
        Optional<JobfairQue> jobfairQue = jobFairQueRepo.findByJobId(jobId);
        if(jobfairQue.isEmpty()){
            throw new JobFairQuestionDetailsNotFoundByJobId("job fair question not found by job id");
        }
        return jobfairQue.get();
    }

    @Override
    public ResponseOfAllJobFairQue addAllJobFairQuestion(List<JobFairQueDto> listOfjobFairQueDto, ResponseOfAllJobFairQue responseOfAllJobFairQue) {
        String exceptionJobIds = "invalid id ";
        boolean flag = false;
        List<JobfairQue> listOfJobFairQue = new ArrayList<>();
        System.out.println("185");

        for(int counter = 0; counter<listOfjobFairQueDto.size();counter++){
            Optional<Job> job = jobRepository.findById(listOfjobFairQueDto.get(counter).getJobId());
            if(job.isEmpty()){
                System.err.println("*");
                exceptionJobIds = exceptionJobIds+": "+listOfjobFairQueDto.get(counter).getJobId();
            }
            else {
                System.err.println(counter);
                JobfairQue jobfairQue = new JobfairQue(listOfjobFairQueDto.get(counter));
                listOfJobFairQue.add(jobfairQue);

            }
        }
        System.out.println("194");
        System.err.println(exceptionJobIds);
        jobFairQueRepo.saveAll(listOfJobFairQue);
        System.out.println("197");

        if(flag){
            responseOfAllJobFairQue.setStatus("!!!!! Success but "+exceptionJobIds);
        }
        responseOfAllJobFairQue.setResponse("job fair question added ");
        return responseOfAllJobFairQue;
    }

//    @Override
//    public JobfairQ1ans getJobFairDetailsByUserId(Integer userId) {
//        Optional<JobfairQ1ans> jobfairQ1ans = jobFairQueRepo.findByUserId(userId);
//        if(jobfairQ1ans.isEmpty()){
//            throw new UserNotFoundException("user not found by id");
//        }
//        return jobfairQ1ans.get();
//    }
}
