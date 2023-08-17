package com.Salaryfy.Dto.PgProgram;

import java.util.List;

public class ResponsePgProgramDto {
    public String status;
    public List<PgProgramDto> response;

    public ResponsePgProgramDto(String status, List<PgProgramDto> message) {
        this.status = status;
        this.response = message;
    }
}
