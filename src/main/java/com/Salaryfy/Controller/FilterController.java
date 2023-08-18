package com.Salaryfy.Controller;

import com.Salaryfy.Dto.FilterDto;
import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Job.ResponseGetAllJobDto;
import com.Salaryfy.Dto.SearchSuggestionDTO;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.FilterService;
import com.Salaryfy.Interfaces.SuggestionService;
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

    @GetMapping("/mainFilter")
    public ResponseEntity<ResponseGetAllJobDto> searchByFilter(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String postName,
            @RequestParam(required = false) List<String> location
            ) {

        FilterDto filterDto = new FilterDto(companyName, postName, location);

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

}
