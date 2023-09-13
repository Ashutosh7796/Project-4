package com.Salaryfy.Controller;

import com.Salaryfy.Dto.Filter.jobSuggest;
import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseGetAllJobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import com.Salaryfy.Interfaces.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class FilterController {
     @Autowired
    private final FilterService filterService;

     private final JobService jobService;

    @GetMapping("/mainFilter")
    public ResponseEntity<ResponseGetAllJobDto> searchByFilter(
            @RequestParam(required = false) List<String> companyName,
            @RequestParam(required = false) List<String> jobType,
            @RequestParam(required = false) List<String> location
    ) {
        FilterDto filterDto = new FilterDto(companyName, jobType, location);

        try {
            List<JobDto> listOfJob = filterService.searchByFilter(filterDto);
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException("page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }
    @GetMapping("/suggest")
    public List<SearchSuggestionDTO> getSuggestions(@RequestParam String query) {
        return filterService.getSuggestions(query);
    }
    @GetMapping("/searchBarFilter")
    public ResponseEntity<?> searchBarFilter(@RequestParam String searchBarInput){
        try {
            List<JobDto> listOfJob = filterService.searchBarFilter(searchBarInput);
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException(String.valueOf(pageNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }
    @GetMapping("/ascendFilter")
    public ResponseEntity<ResponseGetAllJobDto> searchByFilterAndSort(
            @RequestParam(required = false) List<String> companyName,
            @RequestParam(required = false) List<String> jobType,
            @RequestParam(required = false) List<String> location,
            @RequestParam(required = false) String sortDirection,
            @RequestParam(required = false) String sortField
    ) {
        FilterDto filterDto = new FilterDto(companyName, jobType, location);

        try {
            List<JobDto> listOfJob = filterService.searchByFilterAndSort(filterDto, sortField, sortDirection);
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (JobNotFoundException jobNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException("No Matching Data Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }
    @GetMapping("/searchBarFilterSort")
    public ResponseEntity<?> searchBarFilter(
            @RequestParam String searchBarInput,
            @RequestParam String sortDirection,
            @RequestParam String sortField
    ) {

        try {
            List<JobDto> listOfJob = filterService.searchBarFilter(searchBarInput, sortDirection,sortField);
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        }catch (JobNotFoundException e) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
        responseGetAllJobDto.setException("No Matching Data Found");
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);

        }

    }

    @GetMapping("/jobSuggest")
    public ResponseEntity<ResponseGetAllJobDto> searchByFilter(
            @RequestParam(required = false) List<String> postName,
            @RequestParam(required = false) List<String> location,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize
    ) {
        jobSuggest filterDto = new jobSuggest(postName, location);

        try {
            Page<JobDto> jobDtoPage = filterService.suggestJob(filterDto, pageNo, pageSize);
            List<JobDto> listOfJob = jobDtoPage.getContent();
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setList(listOfJob);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccess");
            responseGetAllJobDto.setException("page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }

}
