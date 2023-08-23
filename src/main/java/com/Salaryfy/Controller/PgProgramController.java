package com.Salaryfy.Controller;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;
import com.Salaryfy.Dto.PgProgram.ResponseGetAllPgProgramDto;
import com.Salaryfy.Dto.PgProgram.ResponsePgProgramDto;
import com.Salaryfy.Dto.PgProgram.ResponseSinglePgProgramDto;
import com.Salaryfy.Dto.ResponceDto;
import com.Salaryfy.Exception.PgProgramNotFoundException;
import com.Salaryfy.Interfaces.PgProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAllPg")
    public ResponseEntity<ResponseGetAllPgProgramDto> getAllPg() {
        try {
            List<PgProgramDto> listOfPgPrograms = pgProgramService.getAllPg();
            ResponseGetAllPgProgramDto responseGetAllPgProgramDto = new ResponseGetAllPgProgramDto("success");
            responseGetAllPgProgramDto.setList(listOfPgPrograms);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllPgProgramDto);
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            ResponseGetAllPgProgramDto responseGetAllPgProgramDto = new ResponseGetAllPgProgramDto("unsuccess");
            responseGetAllPgProgramDto.setException("PG program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllPgProgramDto);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updatePgProgramDetailsById(@RequestParam Integer pgProgramId,@RequestBody PgProgramDto pgProgramDto){
        try {
            String result = pgProgramService.updatePgProgramDetailsById(pgProgramId,pgProgramDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result)));
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Program not found Not found"));

        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePgProgramDetailsById(@RequestParam Integer pgProgramId){
        try {
            String result = pgProgramService.deletePgProgramDetailsById(pgProgramId);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", result)));
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Program not found Not found"));

        }
    }
    @GetMapping("/getPgProgramByStatus")
    public ResponseEntity<?> getPgProgramByStatus(@RequestParam String status,@RequestParam Integer pageNo){
        try {
            List<PgProgramDto> result = pgProgramService.getAllPgByStatus(status,pageNo);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponsePgProgramDto("success", result)));
        } catch (PgProgramNotFoundException pgProgramNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", "Program not found Not found"));

        }
    }




}
