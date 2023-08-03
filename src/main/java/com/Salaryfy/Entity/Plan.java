package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId", nullable = false)
    private Integer id;

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

}