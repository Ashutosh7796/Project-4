package com.Salaryfy.Services;


import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseJobDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.JobfairQue;
import com.Salaryfy.Entity.Status;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import com.Salaryfy.Repository.JobRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final JobRepository jobRepository;

    @Override
    public List<JobDto> searchByFilter(FilterDto filterDto) {

        Specification<Job> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getLocation() != null && !filterDto.getLocation().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), filterDto.getLocation()));
            }
            if (filterDto.getPostName() != null && !filterDto.getPostName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("postName"), filterDto.getPostName()));
            }
            if (filterDto.getCompanyName() != null && !filterDto.getCompanyName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("companyName"), filterDto.getCompanyName()));
            }

//            Predicate statusPredicate = criteriaBuilder.or(
//                    criteriaBuilder.equal(root.get("status"), Status.ACTIVE));

//             Predicate statusPredicate = criteriaBuilder.or(
//                    criteriaBuilder.equal(root.get("status").get("status"), Status.ACTIVE.getStatus()));
//            predicates.add(statusPredicate);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

//        Pageable pageable = PageRequest.of(PageNo -0, 10);
//        Page<Job> carPage = jobRepository.findAll(spec, pageable);
//        if(carPage.isEmpty()){
//            throw new PageNotFoundException("Page Not found");
//        }
//        List<JobDto> listOfJobDtos =new ArrayList<>();
//
//        for (int counter=0;counter<carPage.getContent().size();counter++){
//
//            JobDto jobDto = new JobDto(carPage.getContent().get(counter));
//            jobDto.setJobId(carPage.getContent().get(counter).getJobId());
//            listOfJobDtos.add(jobDto);
//        }
//
//        return listOfJobDtos;
//    }
        List<Job> jobs = jobRepository.findAll(spec);
        List<JobDto> listOfJobDtos = new ArrayList<>();

        for (Job job : jobs) {
            JobDto jobDto = new JobDto(job);
            jobDto.setJobId(job.getJobId());
            listOfJobDtos.add(jobDto);
        }

        return listOfJobDtos;
    }

    @Override
    public ResponseJobDto searchBarFilter(String searchBarInput, Integer pageNo, ResponseJobDto responseJobDto) {
        List<Job> jobs = jobRepository.searchJobsByKeyword(searchBarInput);
        List<JobDto> listOfJobDtos = new ArrayList<>();


        if (jobs.size() <= 0) {
            throw new PageNotFoundException("Page not found");
        }

        if ((pageNo * 10) > jobs.size() - 1) {
            throw new PageNotFoundException("page not found");

        }


//        System.out.println("list of de"+listOfCar.size());
        List<JobDto> listOfNewJob = new ArrayList<>();

        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (jobs.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > jobs.size()) {
                break;
            }

            JobDto jobDto = new JobDto(jobs.get(counter));
            jobDto.setUser_Id(jobs.get(counter).getUserUser().getUser_id());

            listOfNewJob.add(jobDto);
            if (diff == i) {
                break;
            }
        }
        responseJobDto.setResponse(listOfJobDtos);

        responseJobDto.setTotalItems(jobs.size());
        Integer totalPages = listOfJobDtos.size() / 10;
        if (listOfJobDtos.size() > totalPages) {
            totalPages++;
        }
        responseJobDto.setTotalPages(totalPages);
        responseJobDto.setCurrentPage(pageNo);

//        System.out.println(listOfCar);
        return responseJobDto;


    }


}




