package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Integer> {
    List<InterviewSchedule> findByUserId(Integer userId);

    @Query("SELECT i FROM InterviewSchedule i " +
            "JOIN i.jobs j " +
            "WHERE i.userId = :userId AND j.jobId = :jobId")
    List<InterviewSchedule> findByUserIdAndJobId(@Param("userId") Integer userId, @Param("jobId") Integer jobId);

    List<InterviewSchedule> findByStatus(String status);

    @Query(value = "SELECT * FROM salaryfy.interview_schedule where user_id=:userId AND interview_date=:interviewDate",nativeQuery = true)
    Optional<Object> findByIdAndInterviewDate(@Param("userId")  Integer userId,@Param("interviewDate")  LocalDate interviewDate);
}
