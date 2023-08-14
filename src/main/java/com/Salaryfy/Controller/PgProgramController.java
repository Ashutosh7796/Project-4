package com.Salaryfy.Controller;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;
import com.Salaryfy.Dto.PgProgram.ResponseSinglePgProgramDto;
import com.Salaryfy.Dto.Plan.PlanDto;
import com.Salaryfy.Dto.Plan.ResponseSinglePlanDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.PgProgramNotFoundException;
import com.Salaryfy.Exception.PlanNotFoundException;
import com.Salaryfy.Interfaces.PgProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pgProgram")
@RequiredArgsConstructor
public class PgProgramController {

    private final PgProgramService pgProgramService;

    @PostMapping("/add")
    public ResponseEntity<ResponceDto> pgProgramAdd(@RequestBody PgProgramDto pgProgramDto) {
        try {
            String result = pgProgramService.AddPg(pgProgramDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result)));
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Program not found Not found"));

        }
    }
    @GetMapping("/getPg")
    public ResponseEntity<ResponseSinglePgProgramDto> FindPgById(@RequestParam Integer PgProgramId) {
        try {
            ResponseSinglePgProgramDto responseSinglePgProgramDto = new ResponseSinglePgProgramDto("success");
            PgProgramDto pgprogram = pgProgramService.findById(PgProgramId);
            responseSinglePgProgramDto.setObject(pgprogram);
            return ResponseEntity.status(HttpStatus.OK).body(responseSinglePgProgramDto);
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            ResponseSinglePgProgramDto responseSinglePgProgramDto = new ResponseSinglePgProgramDto("unsuccess");
            responseSinglePgProgramDto.setException("Pg program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSinglePgProgramDto);
        }
    }

}
