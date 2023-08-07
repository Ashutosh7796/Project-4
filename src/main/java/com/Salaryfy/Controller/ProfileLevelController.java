package com.Salaryfy.Controller;

import com.Salaryfy.Dto.job.ProfileLevelDto.ProfileLevelDto;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profileLevel")
public class ProfileLevelController {

    public ResponseEntity<?> PostProfileLevelData(@RequestBody ProfileLevelDto profileLevelDto){
//        return ResponseEntity.status(HttpStatus.OK).body()
    }
}
