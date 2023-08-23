package com.Salaryfy.Services;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;
import com.Salaryfy.Entity.Pgprogram;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Exception.PageNotFoundException;
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
    public List<PgProgramDto> getAllPg() {
        List<Pgprogram> listOfPgProgram = pgProgramRepository.findAll();
//        System.err.println(listOfPgProgram.size());
//        System.err.println(listOfPgProgram.toString());
        if(listOfPgProgram.size()<=0){throw new PgProgramNotFoundException("Pg program not found");}
        List<PgProgramDto> listOfPgProgramDto = new ArrayList<>();
        for (int counter= 0;counter<listOfPgProgram.size();counter++){
//            System.err.println("********"+counter+"*********** : "+listOfPgProgram.get(counter).toString());
            PgProgramDto pgProgramDto = new PgProgramDto(listOfPgProgram.get(counter));
            listOfPgProgramDto.add(pgProgramDto);

        }
//        System.err.println(listOfPgProgramDto.size());
        return listOfPgProgramDto;
    }



    @Override
    public List<PgProgramDto> getAllPgByStatus(String status,Integer pageNo) {
        List<Pgprogram> listOfPgProgram = pgProgramRepository.getPlansByStatus(status);
        if ((pageNo * 10) > listOfPgProgram.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfPgProgram.size() <= 0) {
            throw new JobNotFoundException("Job not found", HttpStatus.NOT_FOUND);
        }
        List<PgProgramDto> listOfPgProgramDto = new ArrayList<>();

        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfPgProgram.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfPgProgram.size()) {
                break;
            }
            PgProgramDto pgProgramDto = new PgProgramDto(listOfPgProgram.get(counter));
            listOfPgProgramDto.add(pgProgramDto);
            if (diff == i) {
                break;
            }
        }
        return listOfPgProgramDto;
    }

    @Override
    public String updatePgProgramDetailsById(Integer pgProgramId, PgProgramDto pgProgramDto) {
        Optional<Pgprogram> pgprogram = pgProgramRepository.findById(pgProgramId);
        if(pgprogram.isEmpty()){
            throw new PgProgramNotFoundException("pg program not found by id");
        }
        pgProgramRepository.save(pgprogram.get());
        return "PgProgram details updatetd successfully";
    }

    @Override
    public String deletePgProgramDetailsById(Integer pgProgramId) {
        Optional<Pgprogram> pgprogram = pgProgramRepository.findById(pgProgramId);
        if(pgprogram.isEmpty()){
            throw new PgProgramNotFoundException("pg program not found by id");
        }
        pgProgramRepository.save(pgprogram.get());
        return "PgProgram details deleted successfully";
    }
}
