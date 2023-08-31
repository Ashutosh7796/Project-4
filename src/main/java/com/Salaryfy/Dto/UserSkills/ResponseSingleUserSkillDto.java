package com.Salaryfy.Dto.UserSkills;

import lombok.Data;

@Data
public class ResponseSingleUserSkillDto {
    private String message;
    private UserSkillDto response;
    private String exception;

    public ResponseSingleUserSkillDto(String message)
    {
        this.message = message;
    }
}

