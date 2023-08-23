package com.Salaryfy.Services;

import com.Salaryfy.Dto.Plan.PlanDto;
import com.Salaryfy.Entity.Plan;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.PlanNotFoundException;
import com.Salaryfy.Interfaces.PlanService;
import com.Salaryfy.Repository.PlanRepository;
import com.Salaryfy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    private final UserRepository userRepository;

    @Override
    public String AddPlan(PlanDto planDto) {
        Plan plan = new Plan(planDto);
        planRepository.save(plan);
        return "Plan Added";

    }

    @Override
    public String EdgitPlan(PlanDto planDto, Integer PlanId) {
        Plan plan = planRepository.findById(PlanId).orElseThrow(() -> new PlanNotFoundException(("Plan Not Found"), HttpStatus.NOT_FOUND));
        plan.setPlanDays(plan.getPlanDays());
        plan.setPlan(plan.getPlan());
        plan.setAmount(plan.getAmount());
        plan.setStatus(plan.getStatus());
        plan.setUserType(plan.getUserType());
        plan.setUserType(plan.getUserType());
        plan.setService(plan.getService());
        planRepository.save(plan);

        return "Plan Updated" + PlanId;
    }

    @Override
    public String updatePlanFields(PlanDto planDto, Integer PlanId) {
        Plan plan = planRepository.findById(PlanId)
                .orElseThrow(() -> new PlanNotFoundException("Plan Not Found", HttpStatus.NOT_FOUND));

        if (planDto.getPlanDays() != null) {
            plan.setPlanDays(planDto.getPlanDays());
        }
        if (planDto.getPlan() != null) {
            plan.setPlan(planDto.getPlan());
        }
        if (planDto.getAmount() != null) {
            plan.setAmount(planDto.getAmount());
        }
        if (planDto.getStatus() != null) {
            plan.setStatus(planDto.getStatus());
        }
        if (planDto.getUserType() != null) {
            plan.setUserType(planDto.getUserType());
        }
        if (planDto.getService() != null) {
            plan.setService(planDto.getService());
        }

        planRepository.save(plan);

        return "Plan Updated" + PlanId;
    }

    @Override
    public List<PlanDto> getAllPlanWithPages(int PageNo) {
        List<Plan> listOfPlan = planRepository.findAll();
        if ((PageNo * 10) > listOfPlan.size() - 1) {
            throw new PlanNotFoundException("page not found");
        }
        if (listOfPlan.size() <= 0) {
            throw new PlanNotFoundException("Job not found", HttpStatus.NOT_FOUND);
        }
        List<PlanDto> listOfPlanDto = new ArrayList<>();

        int pageStart = PageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfPlan.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfPlan.size()) {
                break;
            }
            PlanDto planDto = new PlanDto(listOfPlan.get(counter));
            planDto.setPlanId(listOfPlan.get(counter).getPlanId());
            listOfPlanDto.add(planDto);
            if (diff == i) {
                break;
            }
        }
        return listOfPlanDto;
    }

    @Override
    public PlanDto findById(Integer PlanId) {
        Optional<Plan> plan = planRepository.findById(PlanId);
        if (plan.isEmpty()) {
            throw new PlanNotFoundException("Plan not found", HttpStatus.NOT_FOUND);
        }
        PlanDto planDto = new PlanDto(plan.get());
        planDto.setPlanId(PlanId);
        return planDto;
    }

    @Override
    public List<PlanDto> getPlanByStatusWithPages(int PageNo, Boolean status) {
        List<Plan> listOfPlansByStatus = planRepository.getPlansByStatus(status);
        if ((PageNo * 10) > listOfPlansByStatus.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfPlansByStatus.size() <= 0) {
            throw new PlanNotFoundException("Plan not found", HttpStatus.NOT_FOUND);
        }
        List<PlanDto> listOfPlanStatus = new ArrayList<>();
        int pageStart = PageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfPlansByStatus.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfPlansByStatus.size()) {
                break;
            }
            PlanDto planDto = new PlanDto(listOfPlansByStatus.get(counter));
            planDto.setPlanId(listOfPlansByStatus.get(counter).getPlanId());
            listOfPlanStatus.add(planDto);
            if (diff == i) {
                break;
            }
        }
        return listOfPlanStatus;
    }

    @Override
    public String deletePlan(Integer planId) {
        PlanDto existingPlan = findById(planId);
        if (existingPlan == null) {
            throw new PlanNotFoundException("Plan with ID " + planId + " not found");
        }
        planRepository.deleteById(planId);

        return "Plan with ID " + planId + " has been deleted successfully";
    }
}
