package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Integer> {
    List<InterviewSchedule> findByUserId(Integer userId);

    @Query("SELECT i FROM InterviewSchedule i " +
            "JOIN i.jobs j " +
            "WHERE i.userId = :userId AND j.jobId = :jobId")
    List<InterviewSchedule> findByUserIdAndJobId(@Param("userId") Integer userId, @Param("jobId") Integer jobId);

    List<InterviewSchedule> findByStatus(String status);



}
