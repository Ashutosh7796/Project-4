package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pgprogram")
public class Pgprogram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PgProgramId", nullable = false)
    private Integer PgProgramId;

    @Column(name = "pgProgramName", length = 250)
    private String pgProgramName;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "CourseDuration", length = 45)
    private String courseDuration;

    @Column(name = "PlacementDuration", length = 45)
    private String placementDuration;

    @Column(name = "SkillDevelopment1", length = 100)
    private String skillDevelopment1;

    @Column(name = "SkillDevelopment2", length = 100)
    private String skillDevelopment2;

    @Column(name = "SkillDevelopment3", length = 100)
    private String skillDevelopment3;

    @Column(name = "SkillDevelopment4", length = 100)
    private String skillDevelopment4;

    @Column(name = "SkillDevelopment5", length = 100)
    private String skillDevelopment5;

    @Column(name = "SkillDevelopment6", length = 100)
    private String skillDevelopment6;

    @Column(name = "SkillDevelopment7", length = 100)
    private String skillDevelopment7;

    @Column(name = "SkillDevelopment8", length = 100)
    private String skillDevelopment8;

    @OneToMany(mappedBy = "pgProgram")
    private List<Pgprogramdoc> pgprogramdocs = new LinkedList<>();

}