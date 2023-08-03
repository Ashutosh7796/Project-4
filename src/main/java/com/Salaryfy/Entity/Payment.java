package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId", nullable = false)
    private Integer id;

    @Column(name = "paymentTanId", length = 250)
    private String paymentTanId;

    @Column(name = "Status", length = 45)
    private String status;

    @Column(name = "date")
    private Instant date;

    @Column(name = "amount", length = 45)
    private String amount;

    @Column(name = "PaymentType", length = 45)
    private String paymentType;

    @Column(name = "planName", length = 250)
    private String planName;

    @Column(name = "PlanId")
    private Integer planId;

    @Column(name = "UserId")
    private Integer userId;

}