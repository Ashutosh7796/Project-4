package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "experiencedoc")
public class Experiencedoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExperienceDocId", nullable = false)
    private Integer ExperienceDocId;

    @Column(name = "CareerBreak")
    private Boolean careerBreak;

    @Column(name = "DurationCareerBreak", length = 100)
    private String durationCareerBreak;

    @Column(name = "LastCompany", length = 250)
    private String lastCompany;

    @Column(name = "PreviousDesignation", length = 45)
    private String previousDesignation;

    @Column(name = "LastDrawnSalary", length = 45)
    private String lastDrawnSalary;

    @Column(name = "WorkExperience", length = 45)
    private String workExperience;

    @Column(name = "CurrentResidingPlace", length = 45)
    private String currentResidingPlace;

    @Column(name = "AvailableToJoin", length = 45)
    private String availableToJoin;

    @Column(name = "highestLevelOfEdu", length = 250)
    private String highestLevelOfEdu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userId", nullable = false)
    private User userUser;

}