package com.Salaryfy.Dto.PgProgram;
import lombok.Data;
import java.util.List;
@Data
public class ResponseGetAllPgProgramDto {private String message;
    private List<PgProgramDto> list;
    private String exception;

    public ResponseGetAllPgProgramDto(String message) {
        this.message = message;
    }
}
