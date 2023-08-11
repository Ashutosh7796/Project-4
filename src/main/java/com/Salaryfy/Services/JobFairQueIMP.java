package com.Salaryfy.Services;

import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Entity.JobfairQue;
import com.Salaryfy.Exception.JobFairQue.JobFairQueNotFoundById;
import com.Salaryfy.Exception.JobFairQue.JobFairQuenotFoundByQueTypeAndSetNo;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.SetNoNotFoundException;
import com.Salaryfy.Interfaces.IJobFairQue;
import com.Salaryfy.Repository.JobFairQueRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobFairQueIMP implements IJobFairQue {
    private final JobFairQueRepo jobFairQueRepo;

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
    public List<JobfairQue> getJobFairDetailsBySetNo(String setNo, Integer pageNo) {

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
}
