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
    private Integer user_id;


    public UserSkillDto(UserSkills userSkills) {
        this.UserSkill = userSkills.getUserSkill();
        this.user_id = userSkills.getUser_id();
    }
}
