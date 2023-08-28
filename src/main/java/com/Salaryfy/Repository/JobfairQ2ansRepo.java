package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import com.Salaryfy.Entity.JobfairQ2ans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobfairQ2ansRepo extends JpaRepository<JobfairQ2ans,Integer> {
    public Optional<JobfairQ2ans> findByUserId(Integer userId);
    @Query("SELECT p FROM JobfairQ2ans p WHERE p.userId = :userId AND p.jobId =:jobId")
    public List<JobfairQ2ans> findByUserIdAndJobId(@Param("userId") Integer userId, Integer jobId);
}
