package com.Salaryfy.Services;

import com.Salaryfy.Dto.ExperienceDocDto;
import com.Salaryfy.Entity.Experiencedoc;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.FillAllDetailsException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.ExperienceDocService;
import com.Salaryfy.Repository.ExperienceDocRepo;
import com.Salaryfy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceDocServiceImpl implements ExperienceDocService {

    @Autowired
    ExperienceDocRepo experienceDocRepo;

    @Autowired
    UserRepository userRepository;

    @Override
    public String saveExperience(ExperienceDocDto experienceDocDto) {
        if (experienceDocDto != null) {
            Optional<User> user = userRepository.findById(experienceDocDto.getUserUser());
            if (user.isPresent()) {
                Experiencedoc experiencedoc = new Experiencedoc(experienceDocDto,user.get());
                experienceDocRepo.save(experiencedoc);
                return "saved";
            } else {
                throw new UserNotFoundException("User not found");
            }
        } else {
            throw new FillAllDetailsException("Fill all the fields");
        }
    }

    @Override
    public List<ExperienceDocDto> findByExperiencedoc(int pageNo, String experience) {
        List<Experiencedoc> listOfUser = experienceDocRepo.findByExperiencedoc(experience);

        if (listOfUser.size() <= 0) {
            throw new UserNotFoundException("User not found", HttpStatus.NOT_FOUND);
        }

        int pageSize = 10;
        int totalUsers = listOfUser.size();
        int totalPages = (totalUsers + pageSize - 1) / pageSize; // Calculate total pages

        if (pageNo >= totalPages) {
            throw new PageNotFoundException("page not found");
        }

        List<ExperienceDocDto> listOfExperienceDocDto = new ArrayList<>();

        int pageStart = pageNo * pageSize;
        int pageEnd = Math.min(pageStart + pageSize, totalUsers); // Ensure end doesn't exceed total users

        for (int counter = pageStart; counter < pageEnd; counter++) {
            ExperienceDocDto experienceDocDto = new ExperienceDocDto(); // Create a new instance here
            experienceDocDto.setWorkExperience(listOfUser.get(counter).getWorkExperience());
            experienceDocDto.setCareerBreak(listOfUser.get(counter).getCareerBreak());
            experienceDocDto.setLastCompany(listOfUser.get(counter).getLastCompany());
            experienceDocDto.setCurrentResidingPlace(listOfUser.get(counter).getCurrentResidingPlace());
            experienceDocDto.setAvailableToJoin(listOfUser.get(counter).getAvailableToJoin());
            experienceDocDto.setDurationCareerBreak(listOfUser.get(counter).getDurationCareerBreak());
            experienceDocDto.setPreviousDesignation(listOfUser.get(counter).getPreviousDesignation());
            experienceDocDto.setLastDrawnSalary(listOfUser.get(counter).getLastDrawnSalary());
            experienceDocDto.setHighestLevelOfEdu(listOfUser.get(counter).getHighestLevelOfEdu());
            experienceDocDto.setUserUser(listOfUser.get(counter).getUserUser().getUser_id());
            listOfExperienceDocDto.add(experienceDocDto); // Add the created instance to the list
        }


        return listOfExperienceDocDto;
    }

    @Override
    public List<ExperienceDocDto> getExperienceDocByCarrierBreak(int pageNo, boolean status) {
        List<Experiencedoc> listOfExperienceDoc = experienceDocRepo.findByCareerBreak(status);
        if ((pageNo * 10) > listOfExperienceDoc.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfExperienceDoc.size() <= 0) {
            throw new UserNotFoundException("User not found", HttpStatus.NOT_FOUND);
        }
        List<ExperienceDocDto> listExperienceDocDto = new ArrayList<>();
        int pageStart = pageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfExperienceDoc.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfExperienceDoc.size()) {
                break;
            }
            ExperienceDocDto experienceDocDto = new ExperienceDocDto(listOfExperienceDoc.get(counter));
            experienceDocDto.setUserUser(listOfExperienceDoc.get(counter).getExperienceDocId());
            listExperienceDocDto.add(experienceDocDto);
            if (diff == i) {
                break;
            }
        }
        return listExperienceDocDto;
    }

}

