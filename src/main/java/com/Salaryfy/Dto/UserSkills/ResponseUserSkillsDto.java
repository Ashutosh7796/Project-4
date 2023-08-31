package com.Salaryfy.Dto.UserSkills;

import lombok.Data;

@Data
public class ResponseUserSkillsDto {
    private String status;
    private String response;
    private String exception;

    public ResponseUserSkillsDto(String status) {
        this.status = status;
    }
}
