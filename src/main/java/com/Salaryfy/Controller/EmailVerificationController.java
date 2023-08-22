package com.Salaryfy.Controller;

import com.Salaryfy.Exception.EmptyFiledException;
import com.Salaryfy.Exception.InvalidOtpException;
import com.Salaryfy.Interfaces.EmailVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EmailVerificationController {

    @Autowired
    EmailVerificationService emailVerificationService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> createOtp(HttpServletRequest request) {
        try {
            String email = request.getParameter("email");

            if (email == null || email.isEmpty()) {
                throw new EmptyFiledException("Email is empty");
            }

            String otp = RandomStringUtils.randomNumeric(4);
            LocalDateTime localDateTime = LocalDateTime.now();
            emailVerificationService.saveEmail(email, otp,localDateTime);
            emailVerificationService.sendEmail(otp, email);

            String sendOtp = "http://169.254.63.118:5173/reset-password?token=" + otp;

            return ResponseEntity.status(HttpStatus.OK).body("Email sent");
        } catch (EmptyFiledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email field is empty");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }


    @PostMapping("/verifyOpt")
    public ResponseEntity<?> VerifyOtp(@RequestParam String otp, @RequestParam String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(emailVerificationService.verifyOtp(otp,email));
        }catch (InvalidOtpException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }

    @Scheduled(fixedRate = 18000) // 1 minute in milliseconds
    public void cleanupExpiredOTP() {
        emailVerificationService.deleteExpiredOTP();
    }

}