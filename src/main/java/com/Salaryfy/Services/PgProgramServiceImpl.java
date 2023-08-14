package com.Salaryfy.Services;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;
import com.Salaryfy.Entity.Pgprogram;
import com.Salaryfy.Exception.PgProgramNotFoundException;
import com.Salaryfy.Interfaces.PgProgramService;
import com.Salaryfy.Repository.PgProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PgProgramServiceImpl implements PgProgramService {
    @Autowired
    private PgProgramRepository pgProgramRepository;
    @Override
    public String AddPg(PgProgramDto pgProgramDto) {
        Pgprogram pgprogram = new Pgprogram(pgProgramDto);
        pgProgramRepository.save(pgprogram);
        return "PgProgram Added";
    }

    @Override
    public String updatePg(PgProgramDto pgProgramDto, Integer PgProgramId) {
        return null;
    }

    @Override
    public PgProgramDto findById(Integer PgProgramId) {
        Optional<Pgprogram> pgprogram=pgProgramRepository.findById(PgProgramId);
      if(pgprogram.isEmpty()){
          throw new PgProgramNotFoundException("Pg Program not found",HttpStatus.NOT_FOUND);
      }
      PgProgramDto pgProgramDto = new PgProgramDto(pgprogram.get());
      pgProgramDto.setPgProgramId(PgProgramId);
      return pgProgramDto;
    }

    @Override
    public List<PgProgramDto> getAllPg(PgProgramDto pgProgramDto) {
        List<Pgprogram> listOfPgProgram = pgProgramRepository.findAll();
        List<PgProgramDto> listOfPgProgramDto = new ArrayList<>();

        for (Pgprogram pgProgram : listOfPgProgram) {
//            PgProgramDto pgProgramDto = new PgProgramDto();
            pgProgramDto.setPgProgramId(pgProgram.getPgProgramId());
            listOfPgProgramDto.add(pgProgramDto);
        }
        return listOfPgProgramDto;
    }

    @Override
    public List<PgProgramDto> getAllPgByStatus(String status) {
        return null;
    }
}
