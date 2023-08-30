package com.Salaryfy.Services;

import com.Salaryfy.Dto.Job.InterviewScheduleDto;
import com.Salaryfy.Entity.InterviewSchedule;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.InterviewScheduleNotFoundException;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.InterviewScheduleService;
import com.Salaryfy.Repository.InterviewScheduleRepository;
import com.Salaryfy.Repository.JobRepository;
import com.Salaryfy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InterviewScheduleServiceImpl implements InterviewScheduleService {

    private final UserRepository userRepository;

    private final InterviewScheduleRepository interviewScheduleRepository;

    private final JobRepository jobRepository;

     @Override
    public InterviewSchedule scheduleInterview(InterviewScheduleDto interviewScheduleDto) {

        User user = userRepository.findById(interviewScheduleDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not found", HttpStatus.NOT_FOUND));

        Job job = jobRepository.findById(interviewScheduleDto.getJobId())
                .orElseThrow(() -> new JobNotFoundException("Job Not found", HttpStatus.NOT_FOUND));

        InterviewSchedule interviewSchedule = new InterviewSchedule(interviewScheduleDto);

        job.getInterviewSchedule().add(interviewSchedule);

        return interviewScheduleRepository.save(interviewSchedule);
    }

    @Override
    public void deleteInterviewScheduleById(Integer interviewScheduleId) {
        InterviewSchedule interviewSchedule = interviewScheduleRepository.findById(interviewScheduleId)
                .orElseThrow(() -> new InterviewScheduleNotFoundException("Interview Schedule Not found by id", HttpStatus.NOT_FOUND));

        List<Job> jobs = interviewSchedule.getJobs();
        for (Job job : jobs) {
            job.getInterviewSchedule().remove(interviewSchedule);
        }

        interviewSchedule.getJobs().clear();
           for (Job job : jobs) {
            jobRepository.save(job);
        }

        interviewScheduleRepository.delete(interviewSchedule);
    }


    @Override
    public List<InterviewScheduleDto> findByUserIdJobId(Integer userId, int jobId) {
        List<InterviewSchedule> interviewSchedules = interviewScheduleRepository.findByUserIdAndJobId(userId, jobId);

        if (interviewSchedules.isEmpty()) {
            throw new InterviewScheduleNotFoundException("Not Found Any Scheduled interview for Id", HttpStatus.NOT_FOUND);
        }

        List<InterviewScheduleDto> interviewScheduleDtos = new ArrayList<>();
        List<Integer> jobIds = new ArrayList<>();

        for (InterviewSchedule interviewSchedule : interviewSchedules) {
            InterviewScheduleDto interviewScheduleDto = new InterviewScheduleDto(interviewSchedule);
            interviewScheduleDto.setUserId(userId);

            List<Job> myjobs = interviewSchedule.getJobs();

            if (!myjobs.isEmpty()) {
                Integer jobIdFromList = myjobs.get(0).getJobId();
                interviewScheduleDto.setJobId(jobIdFromList);
                jobIds.add(jobIdFromList);
            }

            interviewScheduleDtos.add(interviewScheduleDto);
        }

        return interviewScheduleDtos;
    }



    @Override
    public InterviewScheduleDto getinterviewSchedule(int id) {
        Optional<InterviewSchedule> userOptional = interviewScheduleRepository.findById(id);

        if(userOptional.isEmpty()) {

            throw new UserNotFoundException("Not Found Any Scheduled Interview for Id");
        }
        Integer JobId=0;
        List<Job> job = jobRepository.findAll();

        InterviewSchedule interviewSchedule = userOptional.get();

        InterviewScheduleDto interviewScheduleDto = new InterviewScheduleDto(interviewSchedule);
        for (int i=0;i<job.size();i++){

            if(job.get(i).getInterviewSchedule().size() > 0){
                for (int j=0;j<job.get(i).getInterviewSchedule().size();j++){
                    if(job.get(i).getInterviewSchedule().get(j).getInterviewScheduleId() == id){
                        interviewScheduleDto.setJobId(job.get(i).getJobId());
                        break;
                    }
                }
            }
        }
        return interviewScheduleDto;
    }

    @Override
    public List <InterviewScheduleDto> findAllInterviews(int pageNo) {

        List<InterviewSchedule> listOfInterviews = interviewScheduleRepository.findAll();

        
        if((pageNo*10)>listOfInterviews.size()-1){
            throw new PageNotFoundException("page not found");

        }
        if(listOfInterviews.size()<=0){throw new InterviewScheduleNotFoundException("Interview Schedule not found", HttpStatus.NOT_FOUND);
        }

        List<InterviewScheduleDto> listOfInterviewsDto = new ArrayList<>();

        int pageStart=pageNo*10;
        int pageEnd=pageStart+10;
        int diff=(listOfInterviews.size()) - pageStart;
        List<Job> job = jobRepository.findAll();
        for(int counter=pageStart,i=1;counter<pageEnd;counter++,i++){

            if(pageStart>listOfInterviews.size()){break;}


            InterviewScheduleDto interviewScheduleDto = new InterviewScheduleDto (listOfInterviews.get(counter));
            for (int iCounter=0;iCounter<job.size();iCounter++){

                if(job.get(iCounter).getInterviewSchedule().size() > 0){
                    for (int j=0;j<job.get(iCounter).getInterviewSchedule().size();j++){
                        if(job.get(iCounter).getInterviewSchedule().get(j).getInterviewScheduleId() == listOfInterviews.get(counter).getInterviewScheduleId()){
                            interviewScheduleDto.setJobId(job.get(iCounter).getJobId());
                            break;
                        }
                    }
                }
            }
            interviewScheduleDto.setInterviewScheduleId(listOfInterviews.get(counter).getInterviewScheduleId());
            listOfInterviewsDto.add(interviewScheduleDto);


            if(diff == i){
                break;
            }
        }
        return listOfInterviewsDto;


    }

    @Override
    public List<InterviewScheduleDto> findInterviewByUSerId(Integer userId) {

        Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not found ", HttpStatus.NOT_FOUND)));
        List<InterviewSchedule> interviewsByUserId = interviewScheduleRepository.findByUserId(userId);

        if (interviewsByUserId.isEmpty()) {
            throw new InterviewScheduleNotFoundException("No Scheduled Interview found for user", HttpStatus.NOT_FOUND);
        }

        return interviewsByUserId.stream()
                .map(inter -> {
                    InterviewScheduleDto interviewScheduleDtos = new InterviewScheduleDto(inter);
                    interviewScheduleDtos.setUserId(userId);

                    List<Job> myjobs = inter.getJobs();

                    if (!myjobs.isEmpty()) {
                        Integer jobId = myjobs.get(0).getJobId();
                        interviewScheduleDtos.setJobId(jobId);
                    }

                    return interviewScheduleDtos;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewScheduleDto> findInterviewsByStatus(String status, int pageNo) {
        List<InterviewSchedule> interviewsByStatus = interviewScheduleRepository.findByStatus(status);

        if (interviewsByStatus.isEmpty()) {
            throw new InterviewScheduleNotFoundException("No Scheduled Interview found for status: " + status, HttpStatus.NOT_FOUND);
        }

        List<InterviewScheduleDto> interviewScheduleDtos = new ArrayList<>();

        for (InterviewSchedule interviewSchedule : interviewsByStatus) {
            InterviewScheduleDto interviewScheduleDto = new InterviewScheduleDto(interviewSchedule);
            interviewScheduleDtos.add(interviewScheduleDto);
        }


        if((pageNo*10)>interviewsByStatus.size()-1){
            throw new PageNotFoundException("page not found");

        }
        if(interviewsByStatus.size()<=0){throw new InterviewScheduleNotFoundException("Interview Schedule not found", HttpStatus.NOT_FOUND);
        }

        List<InterviewScheduleDto> listOfInterviewsDto = new ArrayList<>();

        int pageStart=pageNo*10;
        int pageEnd=pageStart+10;
        int diff=(interviewsByStatus.size()) - pageStart;
        List<Job> job = jobRepository.findAll();
        for(int counter=pageStart,i=1;counter<pageEnd;counter++,i++){

            if(pageStart>interviewsByStatus.size()){break;}


            InterviewScheduleDto interviewScheduleDto = new InterviewScheduleDto (interviewsByStatus.get(counter));
            for (int iCounter=0;iCounter<job.size();iCounter++){

                if(job.get(iCounter).getInterviewSchedule().size() > 0){
                    for (int j=0;j<job.get(iCounter).getInterviewSchedule().size();j++){
                        if(job.get(iCounter).getInterviewSchedule().get(j).getInterviewScheduleId() == interviewsByStatus.get(counter).getInterviewScheduleId()){
                            interviewScheduleDto.setJobId(job.get(iCounter).getJobId());
                            break;
                        }
                    }
                }
            }
            interviewScheduleDto.setInterviewScheduleId(interviewsByStatus.get(counter).getInterviewScheduleId());
            listOfInterviewsDto.add(interviewScheduleDto);


            if(diff == i){
                break;
            }
        }

        return interviewScheduleDtos;
    }



}
