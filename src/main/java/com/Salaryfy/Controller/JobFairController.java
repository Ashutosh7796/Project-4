package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobFairQue.JobFairIdDto;
import com.Salaryfy.Dto.JobFairQue.JobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseJobFairQueDto;
import com.Salaryfy.Dto.JobFairQue.ResponseOfAllJobFairQue;
import com.Salaryfy.Dto.JobfairQ1ans.ResponseForJobFairOneUserID;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Dto.ResponseDto;
import com.Salaryfy.Exception.JobFairQue.JobFairQueNotFoundById;
import com.Salaryfy.Exception.JobFairQue.JobFairQuenotFoundByQueTypeAndSetNo;
import com.Salaryfy.Exception.JobFairQuestionDetailsNotFoundByJobId;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.SetNoNotFoundException;
import com.Salaryfy.Interfaces.IJobFairQue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responceDto);
        }
    }
    @PostMapping("/saveAllJobFairques")
    public ResponseEntity<?> addAllJobFairques(@RequestBody List<JobFairQueDto> listOfjobFairQueDto){
        System.err.println("42"+listOfjobFairQueDto.size());
        try{
            System.out.println("1");
            ResponseOfAllJobFairQue responseOfAllJobFairQue = new ResponseOfAllJobFairQue("success");
            System.out.println("2");
            return ResponseEntity.status(HttpStatus.OK).body(iJobFairQue.addAllJobFairQuestion(listOfjobFairQueDto,responseOfAllJobFairQue));

        }catch (Exception e){
            ResponceDto responceDto = new ResponceDto("unsuccess","Job Question not added");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responceDto);
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
            responseJobFairQ1Dto.setCurrentPage(pageNo);
            responseJobFairQ1Dto.setResponse(iJobFairQue.getJobFairDetailsBySetNo(setNo,pageNo,responseJobFairQ1Dto));
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
    public ResponseEntity<?> getJobFairDetailsBySetNoAndQueType(@RequestParam String setNo,@RequestParam String questionType){
        try{
            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("success");
            responseJobFairQ1Dto.setResponse(iJobFairQue.getJobFairDetailsBySetNoAndQueType(setNo,questionType));
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

    @GetMapping("/getJobFairDetailsByJobId")
    public ResponseEntity<?> getJobFairDetailsByJobId(@RequestParam Integer jobId){
        try {
            ResponseDto responseDto = new ResponseDto("success");
            responseDto.setResponse(iJobFairQue.getJobFairDetailsByJobId(jobId));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);

        }catch ( JobFairQuestionDetailsNotFoundByJobId jobFairQuestionDetailsNotFoundByJobId) {

            ResponseJobFairQueDto responseJobFairQ1Dto = new ResponseJobFairQueDto("unsuccess");
            responseJobFairQ1Dto.setException(String.valueOf(jobFairQuestionDetailsNotFoundByJobId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJobFairQ1Dto);
        }
    }
}
