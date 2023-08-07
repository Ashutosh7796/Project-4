package com.Salaryfy.Services;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Entity.ProfileLevel;
import com.Salaryfy.Interfaces.IProfileLevel;
import com.Salaryfy.Repository.ProfileLevelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileLevelImp implements IProfileLevel {
    @Autowired
    private ProfileLevelRepo profileLevelRepo;
    @Override
    public String saveProfileLevelData(ProfileLevelDto profileLevelDto) {

        ProfileLevel profileLevel = new ProfileLevel(profileLevelDto);
        profileLevelRepo.save(profileLevel);
        return "Profile level deatils posted successfully";
    }

    @Override
    public String getAllProfileLevelDetails(Integer pageNo) {
        return null;
    }

    @Override
    public String getProfileLevelDetails(Integer profileId) {
        return null;
    }

    @Override
    public String deleteProfileById(Integer profileId) {
        return null;
    }
}
