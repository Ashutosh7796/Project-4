package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobFairQue.JobFairIdDto;
import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.JobFairQue.JobFairQueNotFoundById;
import com.Salaryfy.Exception.JobFairQue.JobFairQuenotFoundByQueTypeAndSetNo;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.SetNoNotFoundException;
import com.Salaryfy.Interfaces.IJobFairQue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/JobFair")
public class JobFairController {
    private final IJobFairQue iJobFairQue;
    @PostMapping("/saveJobFairDetails")
    public ResponseEntity<?> addDetails(@RequestBody JobFairQueDto jobFairQueDto){
        try{
            ResponceDto responceDto = new ResponceDto("success",iJobFairQue.addJobFairQuestion(jobFairQueDto));
            return ResponseEntity.status(HttpStatus.OK).body(responceDto);

        }catch (Exception e){
            ResponceDto responceDto = new ResponceDto("unsuccess","Job Question not added ");
            return ResponseEntity.status(HttpStatus.OK).body(responceDto);
        }
    }
    @GetMapping("/getJobFairDetailsById")
    public ResponseEntity<?> getJobFairDetails(@RequestParam Integer jobFairQueId ){
        try {
            JobFairIdDto jobFairQueDto = new JobFairIdDto("success");
            jobFairQueDto.setJobfairQue(iJobFairQue.getJobFairDetails(jobFairQueId));
            return ResponseEntity.status(HttpStatus.OK).body(jobFairQueDto);

        }catch (JobFairQueNotFoundById jobFairQueNotFoundById){
            JobFairIdDto jobFairQueDto = new JobFairIdDto("unsuccess");
            jobFairQueDto.setException(String.valueOf(jobFairQueNotFoundById));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jobFairQueDto);
        }
    }
    @GetMapping("/getAllJobFairDetails")
    public ResponseEntity<?> getAllJobFairDetails(@RequestParam Integer pageNo){
        try{
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("success");
            return ResponseEntity.status(HttpStatus.OK).body(iJobFairQue.getAllJobFairDetails(pageNo,responseJobFairQ1Dto));
        }catch (SetNoNotFoundException setNoNotFoundException){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(setNoNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }catch (PageNotFoundException pageNotFoundException){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(pageNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }
    }
    @GetMapping("/getJobFairDetailsBySetNo")
    public ResponseEntity<?> getJobFairDetailsBySetNo(@RequestParam String setNo,@RequestParam Integer pageNo){
        try{
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("success");
            responseJobFairQ1Dto.setResponse(iJobFairQue.getJobFairDetailsBySetNo(setNo,pageNo));
            return ResponseEntity.status(HttpStatus.OK).body(responseJobFairQ1Dto);
        }catch (SetNoNotFoundException setNoNotFoundException){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(setNoNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }catch (PageNotFoundException pageNotFoundException){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(pageNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }
    }
    @GetMapping("/getJobFairDetailsBySetNoAndQueType")
    public ResponseEntity<?> getJobFairDetailsBySetNoAndQueType(@RequestParam String setNo,@RequestParam Boolean questionType,@RequestParam Integer pageNo){
        try{
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("success");
            responseJobFairQ1Dto.setResponse(iJobFairQue.getJobFairDetailsBySetNoAndQueType(setNo,questionType,pageNo));
            return ResponseEntity.status(HttpStatus.OK).body(responseJobFairQ1Dto);
        }catch (JobFairQuenotFoundByQueTypeAndSetNo jobFairQuenotFoundByQueTypeAndSetNo){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(jobFairQuenotFoundByQueTypeAndSetNo));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }catch (PageNotFoundException pageNotFoundException){
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(pageNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }
    }

//    @GetMapping("/getJobFairDetailsByUserId")
//    public ResponseEntity<?> getJobFairDetailsByUserId(@RequestParam Integer userId){
//        try {
//            ResponseForJobFairOneUserID responseJobFairQ1Dto = new ResponseForJobFairOneUserID("success");
//            responseJobFairQ1Dto.setResponse(iJobFairQue.get(userId));
//            return ResponseEntity.status(HttpStatus.OK).body(responseJobFairQ1Dto);
//
//        }catch (UserNotFoundException userNotFoundException) {
//
//            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
//            responseJobFairQ1Dto.setException(String.valueOf(userNotFoundException));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
//        }
//    }
}
