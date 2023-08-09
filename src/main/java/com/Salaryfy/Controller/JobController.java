package com.Salaryfy.Controller;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseGetAllJobDto;
import com.Salaryfy.Dto.Job.ResponseSingleJobDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/job")
@RestController
@RequiredArgsConstructor
public class JobController {


    private final JobService jobService;

    @PostMapping(value ="/add")
    public ResponseEntity<ResponceDto> jobadded(@RequestBody JobDto jobDto){
        try
        {
            String result = jobService.AddJob(jobDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",result)));
        }
        catch (JobNotFoundException jobNotFoundException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess","job Not found"));

        }
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<ResponseGetAllJobDto> getAlljobs(@RequestParam int pageNo){

        try
        {
            List<JobDto> listOfJob = jobService.getAlljobsWithPages(pageNo);
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        }
        catch (JobNotFoundException jobNotFoundException)
        {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException("job not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
        catch (PageNotFoundException pageNotFoundException){
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }

    @GetMapping("/getJob")
    public ResponseEntity<ResponseSingleJobDto> FindJobById(@RequestParam int JobId)
    {
        try
        {
            ResponseSingleJobDto responseSingleJobDto = new ResponseSingleJobDto("success");
            JobDto job = jobService.findById(JobId);
            responseSingleJobDto.setObject(job);
            return ResponseEntity.status(HttpStatus.OK).body(responseSingleJobDto);
        }
        catch (JobNotFoundException jobNotFoundException)
        {
            ResponseSingleJobDto responseSingleJobDto = new ResponseSingleJobDto("unsuccess");
            responseSingleJobDto.setException("Job not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSingleJobDto);
        }
    }
}
