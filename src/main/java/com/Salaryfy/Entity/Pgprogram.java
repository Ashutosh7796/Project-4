package com.Salaryfy.Entity;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
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

    @Column(name = "Status")
    private String status;


    @OneToMany(mappedBy = "pgProgram")
    private List<Pgprogramdoc> pgprogramdocs = new LinkedList<>();


    public Pgprogram(PgProgramDto pgProgramDto) {
        this.pgProgramName = pgProgramDto.getPgProgramName();
        this.startDate = pgProgramDto.getStartDate();
        this.endDate = pgProgramDto.getEndDate();
        this.courseDuration = pgProgramDto.getCourseDuration();
        this.placementDuration = pgProgramDto.getPlacementDuration();
        this.skillDevelopment1 = pgProgramDto.getSkillDevelopment1();
        this.skillDevelopment2 = pgProgramDto.getSkillDevelopment2();
        this.skillDevelopment3 = pgProgramDto.getSkillDevelopment3();
        this.skillDevelopment4 = pgProgramDto.getSkillDevelopment4();
        this.skillDevelopment5 = pgProgramDto.getSkillDevelopment5();
        this.skillDevelopment6 = pgProgramDto.getSkillDevelopment6();
        this.skillDevelopment7 = pgProgramDto.getSkillDevelopment7();
        this.skillDevelopment8 = pgProgramDto.getSkillDevelopment8();
        this.status = pgProgramDto.getStatus();
    }
}