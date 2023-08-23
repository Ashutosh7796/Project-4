package com.Salaryfy.Dto;

import com.Salaryfy.Entity.Experiencedoc;
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

    public ExperienceDocDto(Experiencedoc experiencedoc) {
        this.setWorkExperience(experiencedoc.getWorkExperience());
        this.setHighestLevelOfEdu(experiencedoc.getHighestLevelOfEdu());
        this.setPreviousDesignation(experiencedoc.getPreviousDesignation());
        this.setLastDrawnSalary(experiencedoc.getLastDrawnSalary());
        this.setAvailableToJoin(experiencedoc.getAvailableToJoin());
        this.setCurrentResidingPlace(experiencedoc.getCurrentResidingPlace());
        this.setCareerBreak(experiencedoc.getCareerBreak());
        this.setDurationCareerBreak(experiencedoc.getDurationCareerBreak());
        this.setLastCompany(experiencedoc.getLastCompany());
    }
}