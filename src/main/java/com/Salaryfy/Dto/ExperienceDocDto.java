package com.Salaryfy.Dto;

import com.Salaryfy.Entity.Experiencedoc;
import com.Salaryfy.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDocDto {

    private Boolean careerBreak;
    private String durationCareerBreak;
    private String lastCompany;
    private String previousDesignation;
    private String lastDrawnSalary;
    private String workExperience;
    private String currentResidingPlace;
    private String availableToJoin;
    private String highestLevelOfEdu;
    private int userUser;

    public ExperienceDocDto(ExperienceDocDto experienceDocDto) {
    }

}
