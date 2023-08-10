package com.Salaryfy.Dto.ProfileLevelDto;

import lombok.Data;

import java.util.List;
@Data
public class ResponseSingleProfilelevelDto {
    private String status;
    private ProfileLevelDto response;
    private String exception;

    public ResponseSingleProfilelevelDto(String status) {
        this.status = status;
    }
}
