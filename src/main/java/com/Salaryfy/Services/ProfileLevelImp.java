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
        Optional<ProfileLevel> profileLevelOptional = profileLevelRepo.findByUserId(profileLevelDto.getUserId());

        if (profileLevelOptional.isPresent()) {

            Optional<ProfileLevel> profilelevelDetail = profileLevelRepo.findById(profileLevelOptional.get().getProfileId());
            profilelevelDetail.get().setHighestLevelOfEdu(profileLevelDto.getHighestLevelOfEdu() == null ? null : profileLevelDto.getHighestLevelOfEdu());
            profilelevelDetail.get().setBoard(profileLevelDto.getBoard() == null ? null : profileLevelDto.getBoard());
            profilelevelDetail.get().setStream(profileLevelDto.getStream() == null ? null : profileLevelDto.getStream());
            profilelevelDetail.get().setPercentage(profileLevelDto.getPercentage() == null ? null : profileLevelDto.getPercentage());

            profileLevelRepo.save(profilelevelDetail.get());
            return "profile level detail updated";


        }


        Optional<User> user = userRepository.findById(profileLevelDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        ProfileLevel profileLevel = new ProfileLevel(profileLevelDto);
        profileLevel.setUserUser(user.get());
        profileLevelRepo.save(profileLevel);
//        userRepository.findById(profileLevelDto.getUserId()).orElseThrow(() -> new UserNotFoundException("user not found")).ifPresent(user -> profileLevelRepo.save(new ProfileLevel(profileLevelDto, user)));

        return "Profile level deatils posted successfully";
    }

    @Override
    public String getAllProfileLevelDetails(Integer pageNo) {
        return null;
    }

    @Override
    public ProfileLevelDto getProfileLevelDetails(Integer profileId) {

        Optional<ProfileLevel> profileLevel = profileLevelRepo.findById(profileId);
        if (profileLevel.isEmpty()) {
            throw new ProfileLevelIdNotFoundException("profile level details not found by id");
        }
        return new ProfileLevelDto(profileLevel.get());


    }

    @Override
    public String deleteProfileById(Integer profileId) {
        Optional<ProfileLevel> profileLevel = profileLevelRepo.findById(profileId);
        if (profileLevel.isEmpty()) {
            throw new ProfileLevelIdNotFoundException("profile level details not found by id");
        }
        profileLevelRepo.deleteById(profileId);
        return "Profile level deatils deleted successfully";
    }

    @Override
    public String updateProfileLevelDetails(ProfileLevelDto profileLevelDto, Integer profileLevelId) {
//        Optional<ProfileLevel> profileLevel = profileLevelRepo.findById(profileLevelId);
//        if(profileLevel.isEmpty()){throw new ProfileLevelIdNotFoundException("profile level details not found by id");}
//
//        if(profileLevelDto.highestLevelOfEdu!= null){
//            profileLevel.get().setHighestLevelOfEdu(profileLevelDto.highestLevelOfEdu);
//        }
//        if(profileLevelDto.board!= null){
//            profileLevel.get().setBoard(profileLevelDto.board);}
//        if(profileLevelDto.stream!= null){
//            profileLevel.get().setStream(profileLevelDto.stream);}
//        if(profileLevelDto.percentage!= null){
//            profileLevel.get().setPercentage(profileLevelDto.percentage);}
//        profileLevelRepo.save(profileLevel.get());
//        return "profile level detail updated";

        return null;
    }

    @Override
    public ProfileLevelDto getByUserId(Integer userId) {

////        Optional<ProfileLevel> profileLevel = profileLevelRepo.findByUserId(userId);

        Optional<ProfileLevel> profilelevelDetail = null;
        List<ProfileLevel> listOfProfileLevelDto = profileLevelRepo.findAll();
        boolean flag = false;
        for (int counterr = 0; counterr < listOfProfileLevelDto.size(); counterr++) {
            if (listOfProfileLevelDto.get(counterr).getUserUser().getUser_id() == userId) {
                profilelevelDetail = profileLevelRepo.findById(listOfProfileLevelDto.get(counterr).getProfileId());

                return new ProfileLevelDto(profilelevelDetail.get());

            }
        }
        if (profilelevelDetail.isEmpty()) {
            throw new ProfileLevelIdNotFoundException("profile level details not found by userId ");
        }

        return new ProfileLevelDto(profilelevelDetail.get());


    }


}
