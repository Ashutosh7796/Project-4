package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PlacementDetails")
public class PlacementDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlacementDetailsId", nullable = false)
    private Integer PlacementDetailsId;

    @Column(name = "Status", length = 45)
    private String status;

    @Column(name = "Salary", length = 45)
    private String salary;

    @Column(name = "companyName", length = 45)
    private String companyName;

    @Column(name = "UserId")
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Jobs_JobId", nullable = false)
    private Job jobsJob;

}