package com.Salaryfy.Controller;

import com.Salaryfy.Dto.ResponseEmailSentDTO;
import com.Salaryfy.Dto.ResponseEmailVerification;
import com.Salaryfy.Exception.EmptyFiledException;
import com.Salaryfy.Exception.InvalidOtpException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Interfaces.EmailVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/verification")
public class EmailVerificationController {

    @Autowired
    EmailVerificationService emailVerificationService;

    @PostMapping("/sendEmail")
    public ResponseEntity<ResponseEmailSentDTO> createOtp(@RequestParam String email) {
        try {
                String otp = RandomStringUtils.randomNumeric(4);
                LocalDateTime localDateTime = LocalDateTime.now();
                emailVerificationService.sendEmail(otp, email);
                emailVerificationService.saveEmail(email, otp, localDateTime);
                ResponseEmailSentDTO responseEmailSentDTO= new ResponseEmailSentDTO("Email sent");
                responseEmailSentDTO.setStatus("Successful");
                return ResponseEntity.status(HttpStatus.OK).body(responseEmailSentDTO);
        } catch (EmptyFiledException e) {
            ResponseEmailSentDTO responseEmailSentDTO= new ResponseEmailSentDTO("Email field is empty");
            responseEmailSentDTO.setStatus("Unsuccessful");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEmailSentDTO);
        }catch (UserAlreadyExistException e){
            ResponseEmailSentDTO responseEmailSentDTO= new ResponseEmailSentDTO("User Already exist");
            responseEmailSentDTO.setStatus("Unsuccessful");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEmailSentDTO);
        } catch (Exception e) {
            ResponseEmailSentDTO responseEmailSentDTO= new ResponseEmailSentDTO("Something went wrong");
            responseEmailSentDTO.setStatus("Unsuccessful");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEmailSentDTO);
        }
    }


    @PostMapping("/verifyOpt")
    public ResponseEntity<ResponseEmailVerification> VerifyOtp(@RequestParam String otp, @RequestParam String email){
        try {
            String status = emailVerificationService.verifyOtp(otp, email);
            ResponseEmailVerification responseEmailVerification = new ResponseEmailVerification("OTP verified");
            responseEmailVerification.setStatus("Successful");
            return ResponseEntity.status(HttpStatus.OK).body(responseEmailVerification);
        }catch (InvalidOtpException e){
            ResponseEmailVerification responseEmailVerification = new ResponseEmailVerification("Invalid OTP ");
            responseEmailVerification.setStatus("Unsuccessful");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEmailVerification);
        }
    }

    @Scheduled(fixedRate = 18000) // 3 minute in milliseconds
    public void cleanupExpiredOTP() {
        emailVerificationService.deleteExpiredOTP();
    }

}