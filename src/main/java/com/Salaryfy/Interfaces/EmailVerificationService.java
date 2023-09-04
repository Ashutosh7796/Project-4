package com.Salaryfy.Interfaces;

import java.time.LocalDateTime;

public interface EmailVerificationService {

    void sendEmail(String otp, String email);

    String saveEmail(String email, String otp, LocalDateTime localDateTime);

    String verifyOtp(String otp,String email);

    public void deleteExpiredOTP();
}