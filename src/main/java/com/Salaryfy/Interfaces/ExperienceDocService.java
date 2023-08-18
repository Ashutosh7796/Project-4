package com.Salaryfy.Interfaces;


import com.Salaryfy.Dto.ExperienceDocDto;
import com.Salaryfy.Dto.Job.JobDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperienceDocService {

    public String saveExperience(ExperienceDocDto experienceDocDto);

    List<ExperienceDocDto> findByExperiencedoc(int pageNo, String experience);

    List<ExperienceDocDto> getExperienceDocByCarrierBreak(int pageNo, boolean status);
}
