package com.Salaryfy.Controller;

import com.Salaryfy.Dto.ProfileLevelDto.ResponseSingleProfilelevelDto;
import com.Salaryfy.Dto.UserSkills.ResponseSingleUserSkillDto;
import com.Salaryfy.Dto.UserSkills.ResponseUserSkillsDto;
import com.Salaryfy.Dto.UserSkills.UserSkillDto;
import com.Salaryfy.Exception.ProfileLevelIdNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userSkill")
public class UserSkillController {


    @Autowired
    private UserSkillService userSkillService;

    @PostMapping("/add")
    public ResponseEntity<?> addSkills(@RequestBody UserSkillDto userSkillDto) {
        try {
            ResponseUserSkillsDto responseUserSkillsDto = new ResponseUserSkillsDto("Success");
            responseUserSkillsDto.setResponse(userSkillService.AddSkill(userSkillDto));
            return ResponseEntity.status(HttpStatus.OK).body(responseUserSkillsDto);


        } catch (UserNotFoundException userNotFoundException) {
            ResponseUserSkillsDto responseUserSkillsDto = new ResponseUserSkillsDto("unsuccess");
            responseUserSkillsDto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUserSkillsDto);
        }
    }


    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer UserId){
        try {
            ResponseSingleUserSkillDto responseSingleUserSkillDto = new ResponseSingleUserSkillDto("success");
            responseSingleUserSkillDto.setResponse(userSkillService.getById(UserId));
            return ResponseEntity.status(HttpStatus.OK).body(responseSingleUserSkillDto);


        }catch (UserNotFoundException userNotFoundException){
            ResponseSingleUserSkillDto responseSingleUserSkillDto = new ResponseSingleUserSkillDto("unsuccess");
            responseSingleUserSkillDto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSingleUserSkillDto);
        }
    }

}
