package com.Salaryfy.Controller;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Interfaces.IProfileLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profileLevel")
public class ProfileLevelController {
    @Autowired
    private IProfileLevel iProfileLevel;
    @PostMapping("/save")
    public ResponseEntity<?> postProfileLevelData(@RequestBody ProfileLevelDto profileLevelDto){
        return ResponseEntity.status(HttpStatus.OK).body(iProfileLevel.saveProfileLevelData(profileLevelDto));
    }
    @GetMapping("/getAllProfileLevelDetails")
    public ResponseEntity<?> getAllProfileLevelDetails(@RequestBody Integer pageNo){
        return ResponseEntity.status(HttpStatus.OK).body(iProfileLevel.getAllProfileLevelDetails(pageNo));

    }
    @GetMapping("/getProfileLevelDetails")
    public ResponseEntity<?> getProfileLevelDetails(@RequestParam Integer profileId){
        return ResponseEntity.status(HttpStatus.OK).body(iProfileLevel.getProfileLevelDetails(profileId));

    }
    @DeleteMapping("/deleteProfileLevel")
    public ResponseEntity<?> deleteProfileById(@RequestParam Integer profileId){
        return ResponseEntity.status(HttpStatus.OK).body(iProfileLevel.deleteProfileById(profileId));

    }
}
