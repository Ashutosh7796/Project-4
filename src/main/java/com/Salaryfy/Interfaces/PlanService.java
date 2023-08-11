package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Job.JobDto;
import com.Salaryfy.Dto.Plan.PlanDto;

import java.util.List;

public interface PlanService {
    public String AddPlan(PlanDto planDto);

    public String EdgitPlan(PlanDto planDto, Integer PlanId);

    public String updatePlanFields(PlanDto planDto, Integer PlanId);

    public List<PlanDto> getAllPlanWithPages(int PageNo);

    public PlanDto findById(Integer PlanId);

    public List<PlanDto> getPlanByStatusWithPages(int PageNo, Boolean status);

    public String deletePlan(Integer planId);
}


