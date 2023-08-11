package com.Salaryfy.Services;


import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.Status;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import com.Salaryfy.Repository.JobRepository;
import jakarta.persistence.criteria.Predicate;
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
public class FilterServiceImpl implements FilterService {
    @Autowired
    private JobRepository jobRepository;
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

}




