package com.Salaryfy.Controller;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Interfaces.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/job")
@RestController
@RequiredArgsConstructor
public class JobController {

    private JobService jobService;

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


}
