package com.Salaryfy.Controller;

import com.Salaryfy.Dto.Plan.PlanDto;
import com.Salaryfy.Dto.Plan.ResponseGetAllPlanDto;
import com.Salaryfy.Dto.Plan.ResponseSinglePlanDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.PlanNotFoundException;
import com.Salaryfy.Interfaces.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponceDto> planAdded(@RequestBody PlanDto planDto) {
        try {
            String result = planService.AddPlan(planDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result)));
        } catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Plan Not found"));

        }
    }

    @GetMapping("/getAllPlan")
    public ResponseEntity<ResponseGetAllPlanDto> getAllPlan(@RequestParam int pageNo) {

        try {
            List<PlanDto> listOfPlan = planService.getAllPlanWithPages(pageNo);
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("success");
            responseGetAllPlanDto.setList(listOfPlan);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllPlanDto);
        } catch (PlanNotFoundException planNotFoundException) {
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("unsuccess");
            responseGetAllPlanDto.setException("Plan not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllPlanDto);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("unsuccess");
            responseGetAllPlanDto.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllPlanDto);
        }
    }

    @GetMapping("/getPlan")
    public ResponseEntity<ResponseSinglePlanDto> FindPlanById(@RequestParam Integer PlanId) {
        try {
            ResponseSinglePlanDto responseSinglePlanDto = new ResponseSinglePlanDto("success");
            PlanDto plan = planService.findById(PlanId);
            responseSinglePlanDto.setObject(plan);
            return ResponseEntity.status(HttpStatus.OK).body(responseSinglePlanDto);
        } catch (PlanNotFoundException planNotFoundException) {
            ResponseSinglePlanDto responseSinglePlanDto = new ResponseSinglePlanDto("unsuccess");
            responseSinglePlanDto.setException("Plan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSinglePlanDto);
        }
    }

    @GetMapping("/getPlanByStatus")
    public ResponseEntity<ResponseGetAllPlanDto> getPlansByStatusWithPages(@RequestParam int pageNo, boolean status) {
        try {
            List<PlanDto> listOfPlanByStatus = planService.getPlanByStatusWithPages(pageNo, status);
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("success");
            responseGetAllPlanDto.setList(listOfPlanByStatus);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllPlanDto);
        } catch (PlanNotFoundException planNotFoundException) {
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("unsuccess");
            responseGetAllPlanDto.setException("Plan not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllPlanDto);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseGetAllPlanDto responseGetAllPlanDto = new ResponseGetAllPlanDto("unsuccess");
            responseGetAllPlanDto.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllPlanDto);
        }
    }

    @PutMapping("/edit/{PlanId}")
    public ResponseEntity<ResponceDto> planEdit(@RequestBody PlanDto planDto, @PathVariable Integer PlanId) {
        try {
            String result = planService.EdgitPlan(planDto,PlanId);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result)));
        } catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Plan Not found"));

        }
    }


    @PatchMapping("/update/{PlanId}")
    public ResponseEntity<ResponceDto> updatePlanFields(@RequestBody PlanDto planDto, @PathVariable Integer PlanId) {
        try {
            String result = planService.updatePlanFields(planDto,PlanId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result));
        }catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Plan Not found"));

        }
    }

    @DeleteMapping("/delete/{PlanId}")
    public ResponseEntity<ResponceDto> deletePlan(@PathVariable Integer PlanId) {
        try {
            String result = planService.deletePlan(PlanId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result));
        } catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Plan Not found"));
        }
    }
}
