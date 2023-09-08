package com.Salaryfy.Controller;

import com.Salaryfy.Dto.Job.InterviewScheduleDto;
import com.Salaryfy.Dto.Job.ResponseAllScheduleInterviews;
import com.Salaryfy.Dto.Job.ResponseGetSingleInterview;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Entity.InterviewSchedule;
import com.Salaryfy.Exception.InterviewScheduleNotFoundException;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.InterviewScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Interview")
public class InterviewScheduleController {

    private final InterviewScheduleService interviewScheduleService;

    @PostMapping("/Schedule")
    public ResponseEntity<ResponceDto> interviewSchedule(@RequestBody InterviewScheduleDto interviewScheduleDto) {
        try {
            InterviewSchedule interviewSchedule = interviewScheduleService.scheduleInterview(interviewScheduleDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Success", "Interview Scheduled Successfully"));
        } catch (InterviewScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("Unsuccess", e.getMessage()));

        } catch (UserNotFoundException u) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("Unsuccess", u.getMessage()));

        } catch (JobNotFoundException j) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("Unsuccess", j.getMessage()));
        }
    }

    @DeleteMapping("/DleteSchedule/{id}")
    public ResponseEntity<ResponceDto> deleteInterviewSchedule (@PathVariable int id) {
        try{
            interviewScheduleService.deleteInterviewScheduleById(id);
            return ResponseEntity.ok().body(new ResponceDto("Success", "Interview Schedule deleted successfully"));

        } catch (InterviewScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("Unsuccess", e.getMessage()));
        }

    }

    @GetMapping("/interviewSchedule/{id}")

    public ResponseEntity<?> getinterviewSchedule(@PathVariable Integer id) {

        try {
            ResponseGetSingleInterview responceDto = new ResponseGetSingleInterview("Success");

            InterviewScheduleDto interviewScheduleDto = interviewScheduleService.getinterviewSchedule(id);

            responceDto.setObject(interviewScheduleDto);

            return ResponseEntity.status(HttpStatus.OK).body(responceDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("Unsuccess", "Interview Scheduling not found"));
        }
    }

    @GetMapping("/getAllInterviews")
    public ResponseEntity<ResponseAllScheduleInterviews> getAllInterview(@RequestParam int pageNo) {
        try {
            List<InterviewScheduleDto> listofAllInterviews = interviewScheduleService.findAllInterviews(pageNo);
            ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Success");
            responseAllScheduleInterviews.setList(listofAllInterviews);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllScheduleInterviews);

        } catch (InterviewScheduleNotFoundException e) {
            ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Unsuccessful");
            responseAllScheduleInterviews.setException("Scheduled Interview not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllScheduleInterviews);
        } catch (PageNotFoundException p) {
            ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Unsuccessful");
            responseAllScheduleInterviews.setException("Page Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllScheduleInterviews);
        }

    }

    @GetMapping("/getInterviewByUserId")
    public ResponseEntity<?> getInterviewByUserId(@RequestParam Integer userId) {
        {
            try {
                List<InterviewScheduleDto> interviewByUSerId = interviewScheduleService.findInterviewByUSerId(userId);
                ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Success");
                responseAllScheduleInterviews.setList(interviewByUSerId);
                return ResponseEntity.status(HttpStatus.OK).body(responseAllScheduleInterviews);
            } catch (UserNotFoundException e) {
                ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Unsuccess");
                responseAllScheduleInterviews.setException("User Not Found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllScheduleInterviews);
            }
        }
    }

    @GetMapping("/getInterviewsByStatus")
    public ResponseEntity<?> getInterviewsByStatus(@RequestParam String status, @RequestParam int pageNo) {
        try {
            List<InterviewScheduleDto> interviewsByStatus = interviewScheduleService.findInterviewsByStatus(status, pageNo);
            ResponseAllScheduleInterviews response = new ResponseAllScheduleInterviews("Success");
            response.setList(interviewsByStatus);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (InterviewScheduleNotFoundException e) {
            ResponseAllScheduleInterviews response = new ResponseAllScheduleInterviews("Unsuccessful");
            response.setException("Interviews not found for status: " + status);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (PageNotFoundException e) {
            ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Unsuccess");
            responseAllScheduleInterviews.setException("Page Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllScheduleInterviews);
        }
    }


    @GetMapping("getInterviewByUserIdJobId")
    public ResponseEntity<?> getIntByUserIdJobId(@RequestParam Integer userId, @RequestParam int jobId) {
        try {
            List <InterviewScheduleDto> byUserIdJobId = interviewScheduleService.findByUserIdJobId(userId, jobId);
            ResponseAllScheduleInterviews responseGetSingleInterview = new ResponseAllScheduleInterviews("Success");
            responseGetSingleInterview.setList(byUserIdJobId);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetSingleInterview);
        } catch (InterviewScheduleNotFoundException e) {
            ResponseAllScheduleInterviews responseAllScheduleInterviews = new ResponseAllScheduleInterviews("Unsuccess");
            responseAllScheduleInterviews.setException(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllScheduleInterviews);
        }
    }
}

