package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Interfaces.IJobfairQ1ans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobFairOption")
public class JobfairQ1ans {

    @Autowired
    private IJobfairQ1ans iJobfairQ1ans;

    @PostMapping("/addQ1ans")
    public String addQ1ans(@RequestBody JobfairQ1ansDto jobfairQ1ansDto)
    {

        return iJobfairQ1ans.addQ1ans(jobfairQ1ansDto);
    }

    @GetMapping("/getQ1ans")
    public com.Salaryfy.Entity.JobfairQ1ans getQ1ans(@RequestParam Integer JobFairQ1Id)
    {

        return iJobfairQ1ans.getQ1ans(JobFairQ1Id);
    }


    @DeleteMapping("/deleteQ1ans")
    public String deleteQ1ans(@RequestParam Integer jobFairQ1Id)
    {
        return iJobfairQ1ans.deleteQ1ans(jobFairQ1Id);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getallQ1ans(Integer pageNo)
    {
        return (ResponseEntity<?>) iJobfairQ1ans.getallQ1ans(pageNo);
    }



}
