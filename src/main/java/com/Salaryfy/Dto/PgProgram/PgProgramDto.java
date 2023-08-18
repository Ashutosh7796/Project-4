package com.Salaryfy.Dto.PgProgram;

import com.Salaryfy.Entity.Pgprogram;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgProgramDto {
    private Integer PgProgramId;
    private String pgProgramName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String courseDuration;
    private String placementDuration;
    private String skillDevelopment1;
    private String skillDevelopment2;
    private String skillDevelopment3;
    private String skillDevelopment4;
    private String skillDevelopment5;
    private String skillDevelopment6;
    private String skillDevelopment7;
    private String skillDevelopment8;
    private String status;

    public PgProgramDto(Pgprogram pgprogram) {
        this.pgProgramName = pgprogram.getPgProgramName();
        this.startDate = pgprogram.getStartDate();
        this.endDate = pgprogram.getEndDate();
        this.courseDuration = pgprogram.getCourseDuration();
        this.placementDuration = pgprogram.getPlacementDuration();
        this.skillDevelopment1 = pgprogram.getSkillDevelopment1();
        this.skillDevelopment2 = pgprogram.getSkillDevelopment2();
        this.skillDevelopment3 = pgprogram.getSkillDevelopment3();
        this.skillDevelopment4 = pgprogram.getSkillDevelopment4();
        this.skillDevelopment5 = pgprogram.getSkillDevelopment5();
        this.skillDevelopment6 = pgprogram.getSkillDevelopment6();
        this.skillDevelopment7 = pgprogram.getSkillDevelopment7();
        this.skillDevelopment8 = pgprogram.getSkillDevelopment8();
        this.status=pgprogram.getStatus();
    }
}
