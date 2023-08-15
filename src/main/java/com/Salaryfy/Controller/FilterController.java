package com.Salaryfy.Controller;

import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseGetAllJobDto;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/mainFilter/{PageNo}")
    public ResponseEntity<ResponseGetAllJobDto> searchByFilter(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String postName,
            @RequestParam(required = false) List<String> location,
            @PathVariable int PageNo) {

        FilterDto filterDto = new FilterDto(companyName, postName, location);

        try {
            List<JobDto> listOfJob = filterService.searchByFilter(filterDto, PageNo);
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