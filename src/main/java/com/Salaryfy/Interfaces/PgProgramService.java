package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;

import java.util.List;

public interface PgProgramService {

    public String AddPg(PgProgramDto pgProgramDto);

    public String updatePg(PgProgramDto pgProgramDto, Integer PgProgramId);

    public PgProgramDto findById(Integer PgProgramId);

    public List<PgProgramDto> getAllPg(PgProgramDto pgProgramDto);

    public List<PgProgramDto> getAllPgByStatus(String status);


}
