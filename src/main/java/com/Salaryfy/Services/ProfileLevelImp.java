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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileLevelImp implements IProfileLevel {

    private final ProfileLevelRepo profileLevelRepo;
    private final UserRepository userRepository;



    @Override
    public String saveProfileLevelData(ProfileLevelDto profileLevelDto) {
        List<ProfileLevel> listOfProfileLevelDto = profileLevelRepo.findAll();
        boolean flag = false;
        for (int counterr = 0; counterr<listOfProfileLevelDto.size();counterr++){
            if(listOfProfileLevelDto.get(counterr).getUserUser().getUser_id() == profileLevelDto.UserId){
                Optional<ProfileLevel> profilelevelDetail = profileLevelRepo.findById(listOfProfileLevelDto.get(counterr).getProfileId());
                if(profileLevelDto.highestLevelOfEdu!= null){
                    profilelevelDetail.get().setHighestLevelOfEdu(profileLevelDto.highestLevelOfEdu);
                }
                if(profileLevelDto.board!= null){
                    profilelevelDetail.get().setBoard(profileLevelDto.board);}
                if(profileLevelDto.stream!= null){
                    profilelevelDetail.get().setStream(profileLevelDto.stream);}
                if(profileLevelDto.percentage!= null){
                    profilelevelDetail.get().setPercentage(profileLevelDto.percentage);
                }
                profileLevelRepo.save(profilelevelDetail.get());
                return "profile level detail updated";

            }
        }



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

    @Override
    public String updateProfileLevelDetails(ProfileLevelDto profileLevelDto, Integer profileLevelId) {
        Optional<ProfileLevel> profileLevel = profileLevelRepo.findById(profileLevelId);
        if(profileLevel.isEmpty()){throw new ProfileLevelIdNotFoundException("profile level details not found by id");}

        if(profileLevelDto.highestLevelOfEdu!= null){
            profileLevel.get().setHighestLevelOfEdu(profileLevelDto.highestLevelOfEdu);
        }
        if(profileLevelDto.board!= null){
            profileLevel.get().setBoard(profileLevelDto.board);}
        if(profileLevelDto.stream!= null){
            profileLevel.get().setStream(profileLevelDto.stream);}
        if(profileLevelDto.percentage!= null){
            profileLevel.get().setPercentage(profileLevelDto.percentage);}


        profileLevelRepo.save(profileLevel.get());
        return "profile level detail updated";

    }
    @Override
    public ProfileLevelDto getByUserId(Integer userId) {

////        Optional<ProfileLevel> profileLevel = profileLevelRepo.findByUserId(userId);

        Optional<ProfileLevel> profilelevelDetail = null;
        List<ProfileLevel> listOfProfileLevelDto = profileLevelRepo.findAll();
        boolean flag = false;
        for (int counterr = 0; counterr<listOfProfileLevelDto.size();counterr++){
            if(listOfProfileLevelDto.get(counterr).getUserUser().getUser_id() == userId){
                profilelevelDetail= profileLevelRepo.findById(listOfProfileLevelDto.get(counterr).getProfileId());

                return new ProfileLevelDto(profilelevelDetail.get());

            }
        }
        if(profilelevelDetail.isEmpty()){
            throw new ProfileLevelIdNotFoundException("profile level details not found by userId ");
        }

        return new ProfileLevelDto(profilelevelDetail.get());



    }


}
