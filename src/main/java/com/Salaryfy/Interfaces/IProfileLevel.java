package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Entity.ProfileLevel;

import java.util.Optional;

public interface IProfileLevel {
    public String saveProfileLevelData(ProfileLevelDto profileLevelDto);

    public String getAllProfileLevelDetails(Integer pageNo);

    public ProfileLevelDto getProfileLevelDetails(Integer profileId);

    public String deleteProfileById(Integer profileId);

    public String updateProfileLevelDetails(ProfileLevelDto profileLevelDto, Integer profileLevelId);

    public ProfileLevelDto getByUserId(Integer userId) ;
}
