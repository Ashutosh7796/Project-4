package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import com.Salaryfy.Entity.JobfairQ2ans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobfairQ2ansRepo extends JpaRepository<JobfairQ2ans,Integer> {
    public Optional<JobfairQ2ans> findByUserId(Integer userId);
}
