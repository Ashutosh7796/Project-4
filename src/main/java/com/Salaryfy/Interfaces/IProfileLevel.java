package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;

public interface IProfileLevel {
    public String saveProfileLevelData(ProfileLevelDto profileLevelDto);

    public String getAllProfileLevelDetails(Integer pageNo);

    public ProfileLevelDto getProfileLevelDetails(Integer profileId);

    public String deleteProfileById(Integer profileId);

}
