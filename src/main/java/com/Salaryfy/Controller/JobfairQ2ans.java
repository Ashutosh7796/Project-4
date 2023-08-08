package com.Salaryfy.Controller;

import com.Salaryfy.Dto.JobfairQ2ans.JobfairQ2ansDto;
import com.Salaryfy.Interfaces.IJobfairQ2ans;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobfairQ2ans {

    private IJobfairQ2ans iJobfairQ2ans;

    @PostMapping("/addQ2ans")
    public String addQ2ans(@RequestBody JobfairQ2ansDto jobfairQ2ansDto)
    {

        return iJobfairQ2ans.addQ2ans(jobfairQ2ansDto);
    }
}
