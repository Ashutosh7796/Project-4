package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Job.InterviewScheduleDto;
import com.Salaryfy.Entity.InterviewSchedule;

import java.util.List;

public interface InterviewScheduleService {

    InterviewSchedule scheduleInterview(InterviewScheduleDto interviewScheduleDto);

    void deleteInterviewScheduleById(Integer interviewScheduleId);

    List<InterviewScheduleDto> findByUserIdJobId (Integer userId, int jobId);
    InterviewScheduleDto getinterviewSchedule (int id);

    List <InterviewScheduleDto> findAllInterviews(int pageNo);

    List <InterviewScheduleDto> findInterviewByUSerId (Integer userId);
    List<InterviewScheduleDto> findInterviewsByStatus(String status, int pageNo);


}
