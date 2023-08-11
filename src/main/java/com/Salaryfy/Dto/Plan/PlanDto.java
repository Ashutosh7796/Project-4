package com.Salaryfy.Dto.Plan;

import com.Salaryfy.Entity.Plan;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDto {


    private Integer planId;

    private String userType;

    private String service;

    private String plan;

    private Boolean status;

    private String amount;

    private String startDate;

    private String planDays;

    public PlanDto(Plan plan) {
        this.userType = plan.getUserType();
        this.service = plan.getService();
        this.plan = plan.getPlan();
        this.status = plan.getStatus();
        this.amount = plan.getAmount();
        this.startDate = plan.getStartDate();
        this.planDays = plan.getPlanDays();
    }
}

