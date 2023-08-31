package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.UserSkills.UserSkillDto;

public interface UserSkillService {

    public String AddSkill(UserSkillDto userSkillDto);

    public UserSkillDto findbyId(Integer UserSkillsId);

    public UserSkillDto getById(Integer UserId);

    public String deletebyId(Integer UserSkillsId);


}
