package com.Salaryfy.Services;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Dto.UserSkills.UserSkillDto;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Entity.UserSkills;
import com.Salaryfy.Exception.ProfileLevelIdNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Exception.UserSkillNotFoundException;
import com.Salaryfy.Interfaces.UserSkillService;
import com.Salaryfy.Repository.UserRepository;
import com.Salaryfy.Repository.UserSkillRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSkillImpl implements UserSkillService {

    private final UserSkillRepo userSkillRepo;
    private final UserRepository userRepository;


    @Override
    public String AddSkill(UserSkillDto userSkillDto) {
        List<UserSkills> listOfSkillDto = userSkillRepo.findAll();
        boolean flag = false;
        for (int counterr = 0; counterr < listOfSkillDto.size(); counterr++) {
            if (listOfSkillDto.get(counterr).getUserId() == userSkillDto.getUserId()) {
                Optional<UserSkills> userSkillDetailsDetail = userSkillRepo.findById(listOfSkillDto.get(counterr).getUserSkillsId());
                if (userSkillDto.getUserSkill() != null) {
                    userSkillDetailsDetail.get().setUserSkill(userSkillDto.getUserSkill());
                }
                userSkillRepo.save(userSkillDetailsDetail.get());

                return "Skill  updated";

            }
        }
        Optional<User> user = userRepository.findById(userSkillDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        UserSkills userSkills = new UserSkills(userSkillDto);
//        userSkills.setUserUser(user.get());
        userSkillRepo.save(userSkills);
        return "Skill posted successfully";


    }

    @Override
    public UserSkillDto findbyId(Integer UserSkillsId) {
        return null;
    }

    @Override
    public UserSkillDto getById(Integer UserId) {
        Optional<UserSkills> userSkillDetail = null;
        List<UserSkills> listofSkillDto = userSkillRepo.findAll();
        boolean flag = false;
        for (int counterr = 0; counterr < listofSkillDto.size(); counterr++) {
            if (listofSkillDto.get(counterr).getUserId() == UserId) {
                userSkillDetail = userSkillRepo.findById(listofSkillDto.get(counterr).getUserSkillsId());

                UserSkillDto userSkillDto = new UserSkillDto(userSkillDetail.get());
                return userSkillDto;

            }

        }
        if (userSkillDetail.isEmpty()) {
            throw new UserSkillNotFoundException("skill not found by userId ");
        }

        return new UserSkillDto(userSkillDetail.get());


        //        List<UserSkillDto> userSkillDtos = new ArrayList<>();
//
//        List<UserSkills> userSkillsList = userSkillRepo.findAllById(UserId);
//
//        for (UserSkills userSkills : userSkillsList) {
//            UserSkillDto userSkillDto = new UserSkillDto(userSkills);
//            userSkillDtos.add(userSkillDto);
//        }
//
//        return userSkillDtos;


    }

    @Override
    public String deletebyId(Integer UserSkillsId) {
        return null;
    }

}
