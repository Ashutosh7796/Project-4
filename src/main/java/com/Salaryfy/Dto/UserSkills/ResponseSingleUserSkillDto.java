package com.Salaryfy.Dto.UserSkills;

import lombok.Data;

@Data
public class ResponseSingleUserSkillDto {


    private String message;
    private Object object;
    private String exception;

    public ResponseSingleUserSkillDto(String message)
    {
        this.message = message;
    }
}
