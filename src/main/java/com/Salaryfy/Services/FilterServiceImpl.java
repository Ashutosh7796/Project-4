package com.Salaryfy.Services;


import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import com.Salaryfy.Repository.JobRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final JobRepository jobRepository;

    @Override
    public List<JobDto> searchByFilter(FilterDto filterDto) {
        Specification<Job> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getLocation() != null && !filterDto.getLocation().isEmpty()) {
                predicates.add(root.get("location").in(filterDto.getLocation()));
            }
            if (filterDto.getJobType() != null && !filterDto.getJobType().isEmpty()) {
                predicates.add(root.get("jobType").in(filterDto.getJobType()));
            }
            if (filterDto.getCompanyName() != null && !filterDto.getCompanyName().isEmpty()) {
                predicates.add(root.get("companyName").in(filterDto.getCompanyName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
//
//        Pageable pageable = PageRequest.of(PageNo - 0, 10);
//        Page<Job> carPage = jobRepository.findAll(spec, pageable);
//        if (carPage.isEmpty()) {
//            throw new PageNotFoundException("Page Not found");
//        }
//        List<JobDto> listOfJobDtos = new ArrayList<>();

//        for (int counter = 0; counter < carPage.getContent().size(); counter++) {
//            JobDto jobDto = new JobDto(carPage.getContent().get(counter));
//            jobDto.setJobId(carPage.getContent().get(counter).getJobId());
//            listOfJobDtos.add(jobDto);
//        }

        List<Job> filteredJobs = jobRepository.findAll(spec);

        List<JobDto> listOfJobDtos = new ArrayList<>();
        for (Job job : filteredJobs) {
            JobDto jobDto = new JobDto(job);
            listOfJobDtos.add(jobDto);
        }

        return listOfJobDtos;


    }



    @Override
    public List<SearchSuggestionDTO> getSuggestions(String query) {
        List<SearchSuggestionDTO> suggestions = new ArrayList<>();

        Specification<Job> spec = (root, queryBuilder, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), query.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("companyName")), query.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("postName")), query.toLowerCase() + "%")
                );

        Pageable pageable = PageRequest.of(0, 10);
        Page<Job> jobPage = jobRepository.findAll(spec, pageable);

        Set<String> uniqueSuggestions = new HashSet<>();

        for (Job job : jobPage.getContent()) {
            uniqueSuggestions.add(job.getLocation());
            uniqueSuggestions.add(job.getCompanyName());
            uniqueSuggestions.add(job.getPostName());
        }

        suggestions.clear();

        if (uniqueSuggestions.size() == 1) {
            SearchSuggestionDTO suggestionDTO = new SearchSuggestionDTO();
            suggestionDTO.setSuggestion(uniqueSuggestions.iterator().next());
            suggestions.add(suggestionDTO);
        } else {
            for (String suggestion : uniqueSuggestions) {
                SearchSuggestionDTO suggestionDTO = new SearchSuggestionDTO();
                suggestionDTO.setSuggestion(suggestion);
                suggestions.add(suggestionDTO);
            }
        }

        return suggestions;
    }


    @Override
    public List<JobDto> searchBarFilter(String searchBarInput) {

        List<Job> jobs = jobRepository.searchJobsByKeyword(searchBarInput);
        System.err.println(jobs.toString());


        if (jobs.size() <= 0) {
            throw new PageNotFoundException("Page not found");
        }

        /////with out pagination code /////
        List<JobDto> listOfNewJob = new ArrayList<>();

        for (int counter =0; counter<jobs.size(); counter++){
            JobDto jobDto = new JobDto(jobs.get(counter));
            jobDto.setUser_Id(jobs.get(counter).getUserUser().getUser_id());

            listOfNewJob.add(jobDto);
        }
        return listOfNewJob;
        /////pagination code /////
//        System.out.println("list of de"+listOfCar.size());
//        List<JobDto> listOfNewJob = new ArrayList<>();
//
//        int pageStart = pageNo * 10;
//        int pageEnd = pageStart + 10;
//        int diff = (jobs.size()) - pageStart;
//        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
//            if (pageStart > jobs.size()) {
//                break;
//            }
////            System.out.println("inside for lop line no 139 :"+i);
//
//            JobDto jobDto = new JobDto(jobs.get(counter));
//            jobDto.setUser_Id(jobs.get(counter).getUserUser().getUser_id());
//
//            listOfNewJob.add(jobDto);
//            if (diff == i) {
//                break;
//            }
//        }
//        responseJobDto.setResponse(listOfNewJob);
////        System.err.println("Ho"+responseJobDto.getResponse());
//
//        responseJobDto.setTotalItems(jobs.size());
//        Integer totalPages = listOfNewJob.size() / 10;
//        if (listOfNewJob.size() > totalPages) {
//            totalPages++;
//        }
//        responseJobDto.setTotalPages(totalPages);
//        responseJobDto.setCurrentPage(pageNo);
//
////        System.out.println(listOfCar);
//        return responseJobDto;


    }

    @Override
    public List<JobDto> searchBarFilter(String searchBarInput, String sortDirection) {
        List<Job> jobs = jobRepository.searchJobsByKeyword(searchBarInput);

        List<JobDto> listOfNewJob = new ArrayList<>();

        for (Job job : jobs) {
            JobDto jobDto = new JobDto(job);
            jobDto.setUser_Id(job.getUserUser().getUser_id());
            listOfNewJob.add(jobDto);
        }

        if ("asc".equalsIgnoreCase(sortDirection)) {
            listOfNewJob.sort(Comparator.comparing(JobDto::getInterviewStartDate));
        } else if ("desc".equalsIgnoreCase(sortDirection)) {
            listOfNewJob.sort(Comparator.comparing(JobDto::getInterviewStartDate).reversed());
        }

        return listOfNewJob;
    }


    public List<JobDto> searchByFilterAndSort(FilterDto filterDto, String sortField, String sortDirection) {
        Specification<Job> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getLocation() != null && !filterDto.getLocation().isEmpty()) {
                predicates.add(root.get("location").in(filterDto.getLocation()));
            }
            if (filterDto.getJobType() != null && !filterDto.getJobType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("jobType"), filterDto.getJobType()));
            }
            if (filterDto.getCompanyName() != null && !filterDto.getCompanyName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("companyName"), filterDto.getCompanyName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<Job> filteredJobs = jobRepository.findAll(spec);

        List<JobDto> listOfJobDtos = filteredJobs.stream()
                .map(JobDto::new)
                .collect(Collectors.toList());

        Comparator<JobDto> comparator = Comparator.comparing(JobDto::getCompanyName);
        if ("jobType".equals(sortField)) {
            comparator = Comparator.comparing(JobDto::getJobType);
        } else if ("location".equals(sortField)) {
            comparator = Comparator.comparing(JobDto::getLocation);
        } else if ("interviewStartDate".equals(sortField)) {
            comparator = Comparator.comparing(JobDto::getInterviewStartDate, Comparator.nullsLast(Comparator.naturalOrder()));
        }

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return listOfJobDtos.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}





