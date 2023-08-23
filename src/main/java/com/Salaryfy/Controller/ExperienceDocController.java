package com.Salaryfy.Controller;

import com.Salaryfy.Dto.*;
import com.Salaryfy.Exception.FillAllDetailsException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.ExperienceDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceDocController {

    @Autowired
    ExperienceDocService experienceDocService;

    @PostMapping("/saveExperience")
    public ResponseEntity<?> saveExperience(@RequestBody ExperienceDocDto experienceDocDto)  {

        System.out.println(experienceDocDto);

        try {
            String status= experienceDocService.saveExperience(experienceDocDto);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (FillAllDetailsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all the details");
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }

    @GetMapping("/getAllUsersByExperience")
    public ResponseEntity<ResponseAllUsers> getAllUsersByExperience(@RequestParam String experience, @RequestParam int pageNo){

        try {
            List<ExperienceDocDto> experienceDocDto= experienceDocService.findByExperiencedoc(pageNo,experience);
            ResponseAllUsers responseAllUsersDto= new ResponseAllUsers("Success");
            responseAllUsersDto.setUsers(experienceDocDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllUsersDto);
        }catch (PageNotFoundException e){
            ResponseAllUsers responseAllUsersDto= new ResponseAllUsers("Unsuccessful");
            responseAllUsersDto.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllUsersDto);
        }catch (UserNotFoundException e) {
            ResponseAllUsers responseAllUsersDto= new ResponseAllUsers("Unsuccessful");
            responseAllUsersDto.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllUsersDto);
        }

    }

    @GetMapping("/getExperienceDocByCarrierBreak")
    public ResponseEntity<ResponseAllUsers> getExperienceDocByCarrierBreak(@RequestParam int pageNo, boolean status) {

        try {
            List<ExperienceDocDto> listOfJobsByStatus = experienceDocService.getExperienceDocByCarrierBreak(pageNo, status);
            ResponseAllUsers responseAllUsers = new ResponseAllUsers("success");
            responseAllUsers.setUsers(listOfJobsByStatus);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllUsers);
        } catch (UserNotFoundException jobNotFoundException) {
            ResponseAllUsers responseAllUsers = new ResponseAllUsers("Unsuccessful");
            responseAllUsers.setException("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllUsers);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseAllUsers responseAllUsers = new ResponseAllUsers("Unsuccessful");
            responseAllUsers.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllUsers);
        }
    }

}
