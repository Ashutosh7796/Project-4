package com.Salaryfy.Services;

import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Interfaces.JobService;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;
    @Override
    public String AddJob(JobDto jobDto) {
//        User user=userRepository.findById(jobDto.getJobId()).orElseThrow(()->)

        return null;
    }

    @Override
    public String EditJob(JobDto jobDto, Integer JobId) {
        return null;
    }

    @Override
    public String DeleteJob(Integer JobId) {
        return null;
    }
}
