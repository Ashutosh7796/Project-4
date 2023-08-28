package com.Salaryfy.Controller;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import com.Salaryfy.Dto.ProfileLevelDto.ResponseProfileLevelDto;
import com.Salaryfy.Dto.ProfileLevelDto.ResponseSingleProfilelevelDto;
import com.Salaryfy.Exception.ProfileLevelIdNotFoundException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IProfileLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profileLevel")
public class ProfileLevelController {
    @Autowired
    private IProfileLevel iProfileLevel;
    @PostMapping("/save")
    public ResponseEntity<?> postProfileLevelData(@RequestBody ProfileLevelDto profileLevelDto){
        try {
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
            responseProfileLevelDto.setResponse(iProfileLevel.saveProfileLevelData(profileLevelDto));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (UserNotFoundException userNotFoundException){
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
            responseProfileLevelDto.setException(String.valueOf(userNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
        }
    }
//    @PostMapping("/save")
//    public ResponseEntity<?> postProfileLevelData(@RequestBody List<ProfileLevelDto> listOfProfileLevelDto){
//        try {
//            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
//            responseProfileLevelDto.setResponse(iProfileLevel.saveAllProfileLevelData(listOfProfileLevelDto));
//            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);
//
//        }catch (UserNotFoundException userNotFoundException){
//            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
//            responseProfileLevelDto.setException(String.valueOf(userNotFoundException));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
//        }
//    }
    @GetMapping("/getAllProfileLevelDetails")
    public ResponseEntity<?> getAllProfileLevelDetails(@RequestParam Integer pageNo){
        return ResponseEntity.status(HttpStatus.OK).body(iProfileLevel.getAllProfileLevelDetails(pageNo));

    }
    @GetMapping("/getProfileLevelDetails")
    public ResponseEntity<?> getProfileLevelDetails(@RequestParam Integer profileId){
        try {
            ResponseSingleProfilelevelDto responseProfileLevelDto = new ResponseSingleProfilelevelDto("success");
            responseProfileLevelDto.setResponse(iProfileLevel.getProfileLevelDetails(profileId));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (ProfileLevelIdNotFoundException profileLevelIdNotFoundException){
            ResponseSingleProfilelevelDto responseSingleProfilelevelDto = new ResponseSingleProfilelevelDto("unsuccess");
            responseSingleProfilelevelDto.setException(String.valueOf(profileLevelIdNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSingleProfilelevelDto);
        }


    }
    @DeleteMapping("/deleteProfileLevel")
    public ResponseEntity<?> deleteProfileById(@RequestParam Integer profileId){
        try {
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
            responseProfileLevelDto.setResponse(iProfileLevel.deleteProfileById(profileId));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (ProfileLevelIdNotFoundException profileLevelIdNotFoundException){
            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
            responseProfileLevelDto.setException(String.valueOf(profileLevelIdNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
        }
    }
    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer userId){
        try {
            ResponseSingleProfilelevelDto responseProfileLevelDto = new ResponseSingleProfilelevelDto("success");
            responseProfileLevelDto.setResponse(iProfileLevel.getByUserId(userId));
            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);

        }catch (ProfileLevelIdNotFoundException profileLevelIdNotFoundException){
            ResponseSingleProfilelevelDto responseSingleProfilelevelDto = new ResponseSingleProfilelevelDto("unsuccess");
            responseSingleProfilelevelDto.setException(String.valueOf(profileLevelIdNotFoundException));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSingleProfilelevelDto);
        }
    }

//    @PatchMapping("/updateProfileLevel")
//    public ResponseEntity<?> updateProfileLevelDetails(@RequestBody ProfileLevelDto profileLevelDto,@RequestParam Integer profileLevelId){
//        try {
//            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("success");
//            responseProfileLevelDto.setResponse(iProfileLevel.updateProfileLevelDetails(profileLevelDto,profileLevelId));
//            return ResponseEntity.status(HttpStatus.OK).body(responseProfileLevelDto);
//
//        }catch (UserNotFoundException userNotFoundException){
//            ResponseProfileLevelDto responseProfileLevelDto = new ResponseProfileLevelDto("unsuccess");
//            responseProfileLevelDto.setException(String.valueOf(userNotFoundException));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseProfileLevelDto);
//        }
//
//    }
}
