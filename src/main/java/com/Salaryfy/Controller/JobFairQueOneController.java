package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Dto.JobfairQ1ans.ResponseForJobFairOneUserID;
import com.Salaryfy.Dto.JobfairQ1ans.ResponseJobFairQ1Dto;
import com.Salaryfy.Dto.ProfileLevelDto.ResponseProfileLevelDto;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IJobfairQ1ans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobFairOption")
public class JobFairQueOneController {

    @Autowired
    private IJobfairQ1ans iJobfairQ1ans;

    @PostMapping("/save")
    public ResponseEntity<?> addQ1ans(@RequestBody JobfairQ1ansDto jobfairQ1ansDto)
    {
        try {
            System.out.println();
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
            responseProfileLevelDto.setResponse(iJobfairQ1ans.addQ1ans(jobfairQ1ansDto));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (UserNotFoundException userNotFoundException){
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
            responseProfileLevelDto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
        }catch (JobNotFoundException jobNotFoundException){
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
            responseProfileLevelDto.setException(String.valueOf(jobNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
        }

    }

    @GetMapping("/getQ1ans")
    public ResponseEntity<?> getQ1ans(@RequestParam Integer JobFairQ1Id)
    {
        try {
            System.out.println();
            ResponseJobFairQ1Dto responseProfileLevelDto = new ResponseJobFairQ1Dto("success");
            responseProfileLevelDto.setResponse(iJobfairQ1ans.getQ1ans(JobFairQ1Id));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (JobNotFoundException userNotFoundException){
            ResponseJobFairQ1Dto responseJobFairQ1Dto = new ResponseJobFairQ1Dto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }


    }


    @DeleteMapping("/deleteQ1ans")
    public ResponseEntity<?> deleteQ1ans(@RequestParam Integer jobFairQ1Id)
    {
        try {
            System.out.println();
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
            responseProfileLevelDto.setResponse(iJobfairQ1ans.deleteQ1ans(jobFairQ1Id));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (JobNotFoundException userNotFoundException){
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
            responseProfileLevelDto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
        }

    }
    @GetMapping("/getJobFairDetailsByUserId")
    public ResponseEntity<?> getJobFairDetailsByUserId(@RequestParam Integer userId){
        try {
            ResponseForJobFairOneUserID responseJobFairQ1Dto = new ResponseForJobFairOneUserID("success");
            responseJobFairQ1Dto.setResponse(iJobfairQ1ans.getJobFairDetailsByUserId(userId));
            return ResponseEntity.status(HttpStatus.OK).body(responseJobFairQ1Dto);

        }catch (UserNotFoundException userNotFoundException) {

            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }
    }
//    @GetMapping("/getall")
//    public ResponseEntity<?> getallQ1ans(Integer pageNo)
//    {
//        return (ResponseEntity<?>) iJobfairQ1ans.getallQ1ans(pageNo);
//    }
//


}
