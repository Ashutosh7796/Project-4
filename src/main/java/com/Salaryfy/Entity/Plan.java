package com.Salaryfy.Entity;

import com.Salaryfy.Dto.Plan.PlanDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId", nullable = false)
    private Integer planId;

    @Column(name = "userType", length = 45)
    private String userType;

    @Column(name = "service", length = 45)
    private String service;

    @Column(name = "plan", length = 45)
    private String plan;

    @Column(name= "Plan_Status")
    private Boolean status;

    @Column(name = "amount", length = 45)
    private String amount;

    @Column(name = "startDate", length = 45)
    private String startDate;

    @Column(name = "planDays", length = 45)
    private String planDays;

    public Plan(PlanDto planDto) {
        this.userType = planDto.getUserType();
        this.service = planDto.getService();
        this.plan = planDto.getPlan();
        this.status = planDto.getStatus();
        this.amount = planDto.getAmount();
        this.startDate = planDto.getStartDate();
        this.planDays = planDto.getPlanDays();
    }
}