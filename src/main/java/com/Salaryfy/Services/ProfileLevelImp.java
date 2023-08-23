package com.Salaryfy.Services;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Entity.ProfileLevel;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.ProfileLevelIdNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IProfileLevel;
import com.Salaryfy.Repository.ProfileLevelRepo;
import com.Salaryfy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileLevelImp implements IProfileLevel {

    private final ProfileLevelRepo profileLevelRepo;
    private final UserRepository userRepository;



    @Override
    public String saveProfileLevelData(ProfileLevelDto profileLevelDto) {
        Optional<User> user= userRepository.findById(profileLevelDto.getUserId());
        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        ProfileLevel profileLevel = new ProfileLevel(profileLevelDto);
        profileLevel.setUserUser(user.get());
        profileLevelRepo.save(profileLevel);
        return "Profile level deatils posted successfully";
    }

    @Override
    public String getAllProfileLevelDetails(Integer pageNo) {
        return null;
    }

    @Override
    public ProfileLevelDto getProfileLevelDetails(Integer profileId) {

        Optional<ProfileLevel> profileLevel = profileLevelRepo.findById(profileId);
        if(profileLevel.isEmpty()){
            throw new ProfileLevelIdNotFoundException("profile level details not found by id");
        }
        return new ProfileLevelDto(profileLevel.get());


    }

    @Override
    public String deleteProfileById(Integer profileId) {
        Optional<ProfileLevel> profileLevel= profileLevelRepo.findById(profileId);
        if (profileLevel.isEmpty()){
            throw new ProfileLevelIdNotFoundException("profile level details not found by id");
        }
        profileLevelRepo.deleteById(profileId);
        return "Profile level deatils deleted successfully";
    }
}
