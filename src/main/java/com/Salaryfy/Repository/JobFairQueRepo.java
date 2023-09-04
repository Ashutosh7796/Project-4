package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobFairQueRepo extends JpaRepository<JobfairQue,Integer> {
//    public Optional<JobfairQ1ans> findByUserId(Integer userId);
    public List<JobfairQue> findBySetNo(String setNo);
    @Query("SELECT jfq FROM JobfairQue jfq WHERE jfq.questionType = :questionType AND jfq.setNo = :setNo")
    public List<JobfairQue> findByQueTypeAndSetNo(@Param("questionType") String questionType,@Param("setNo")String setNo);

    public List<JobfairQue> findByJobId(Integer jobId);
}
