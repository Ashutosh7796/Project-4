package com.Salaryfy.Controller;

import com.Salaryfy.Interfaces.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OTPCleanupScheduler {

 /*   @Autowired
    EmailVerificationService emailVerificationService;

    @DeleteMapping("/delete")
    @Scheduled(fixedRate = 60000) // 1 minute in milliseconds
    public void cleanupExpiredOTP() {
        emailVerificationService.deleteExpiredOTP();
    }*/
}
