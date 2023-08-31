package com.Salaryfy.Dto.UserSkills;

import com.Salaryfy.Entity.UserSkills;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSkillDto {


    private Integer UserSkillsId;
    private String UserSkill;
    private Integer UserId;


    public UserSkillDto(UserSkills userSkills) {
        this.UserSkillsId=userSkills.getUserSkillsId();
        this.UserSkill = userSkills.getUserSkill();
        this.UserId = userSkills.getUserId();
    }
}