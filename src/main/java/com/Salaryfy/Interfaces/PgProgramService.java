package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.PgProgram.PgProgramDto;

import java.util.List;

public interface PgProgramService {

    public String AddPg(PgProgramDto pgProgramDto);


    public PgProgramDto findById(Integer PgProgramId);

    public List<PgProgramDto> getAllPg();

    public List<PgProgramDto> getAllPgByStatus(String status,Integer pageNo);

    public String updatePgProgramDetailsById(Integer pgProgramId, PgProgramDto pgProgramDto);

    public String deletePgProgramDetailsById(Integer pgProgramId);
}
