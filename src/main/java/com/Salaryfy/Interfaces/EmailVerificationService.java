package com.Salaryfy.Interfaces;

public interface EmailVerificationService {

    void sendEmail(String otp, String email);

    void saveEmail(String email, String otp);

    String verifyOtp(String otp,String email);
}