package com.Salaryfy.Controller;

import com.Salaryfy.Exception.InvalidOtpException;
import com.Salaryfy.Interfaces.EmailVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailVerificationController {

    @Autowired EmailVerificationService emailVerificationService;

    @PostMapping("/sendEmail")
    public String CreateOtp(HttpServletRequest request)  {

        String email = request.getParameter("email");
        String otp = RandomStringUtils.randomNumeric(4);

        emailVerificationService.saveEmail(email,otp);

        emailVerificationService.sendEmail(otp, email);

        String sendOtp = "http://169.254.63.118:5173/reset-password?token=" + otp;

       // emailVerificationService.forgotPass(email, sendOtp);

        return "okk";
    }

    @PostMapping("/verifyOpt")
    public ResponseEntity<?> VerifyOtp(@RequestParam String otp, @RequestParam String email){
        try {
             return ResponseEntity.status(HttpStatus.OK).body(emailVerificationService.verifyOtp(otp,email));
        }catch (InvalidOtpException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }
}
