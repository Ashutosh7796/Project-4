package com.Salaryfy.Dto.UserSkills;

import lombok.Data;

import java.util.List;
@Data
public class ResponseAllUserSkillsAllDto {  private String message;
    private List<UserSkillDto> list;
    private String exception;

    public ResponseAllUserSkillsAllDto(String message) {
        this.message = message;
    }


}
