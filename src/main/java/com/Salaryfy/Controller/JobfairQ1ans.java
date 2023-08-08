package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobfairQ1ans.JobfairQ1ansDto;
import com.Salaryfy.Interfaces.IJobfairQ1ans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobfairQ1ans {

    @Autowired
    private IJobfairQ1ans iJobfairQ1ans;

    @PostMapping("/addQ1ans")
    public String addQ1ans(@RequestBody JobfairQ1ansDto jobfairQ1ansDto)
    {

        return iJobfairQ1ans.addQ1ans(jobfairQ1ansDto);
    }

    @GetMapping("/getQ1ans")
    public com.Salaryfy.Entity.JobfairQ1ans getQ1ans(@RequestParam int JobFairQ1Id)
    {

        return iJobfairQ1ans.getQ1ans(JobFairQ1Id);
    }

    @GetMapping("/getall")
    public List<com.Salaryfy.Entity.JobfairQ1ans> getQ1ansall()
    {
         return iJobfairQ1ans.getallQ1ans();
    }

    @DeleteMapping("/deleteallQ1ans")
    public String deleteallQ1ans()
    {
        return iJobfairQ1ans.deleteallQ1ans();
    }

    @DeleteMapping("/deleteQ1ans")
    public String deleteQ1ans(@RequestParam int jobFairQ1Id)
    {
        return iJobfairQ1ans.deleteQ1ans(jobFairQ1Id);
    }

    @PutMapping("/updateQ1ans")
    public String updateQ1ans(@RequestParam int jobFairQ1Id)
    {

        return iJobfairQ1ans.updateQ1ans(jobFairQ1Id);
    }


}
