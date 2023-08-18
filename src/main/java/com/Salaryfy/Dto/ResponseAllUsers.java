package com.Salaryfy.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAllUsers {

    String status;
    List<ExperienceDocDto> users;
    String exception;

    public ResponseAllUsers(String status) {
        this.status=status;
    }
}
