package com.Salaryfy.Repository;

import com.Salaryfy.Entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Integer> {
    List<InterviewSchedule> findByUserId(Integer userId);

    List<InterviewSchedule> findByStatus(String status);



}
