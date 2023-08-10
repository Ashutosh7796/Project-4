package com.Salaryfy.Repository;

import com.Salaryfy.Entity.JobfairQue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobFairQueRepo extends JpaRepository<JobfairQue,Integer> {
    public List<JobfairQue> findBySetNo(String setNo);
    @Query("SELECT jfq FROM JobfairQue jfq WHERE jfq.questionType = :questionType AND jfq.setNo = :setNo")
    public List<JobfairQue> findByQueTypeAndSetNo(@Param("questionType") Boolean questionType,@Param("setNo")String setNo);
}
