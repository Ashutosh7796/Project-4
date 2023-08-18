package com.Salaryfy.Dto.PgProgram;
import lombok.Data;

@Data
public class ResponseSinglePgProgramDto {
    private String message;
    private Object object;
    private String exception;

    public ResponseSinglePgProgramDto(String message)
    {
        this.message = message;
    }
}
