package com.Salaryfy.Dto.UserSkills;

import java.util.List;

public class responseUserSkillsDto {
    public String status;
    public List<UserSkillDto> response;

    public responseUserSkillsDto(String status, List<UserSkillDto> message) {
        this.status = status;
        this.response = message;
    }
}
