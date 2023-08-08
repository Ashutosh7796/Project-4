package com.Salaryfy.Services;

import com.Salaryfy.Entity.EmailVerification;
import com.Salaryfy.Exception.InvalidOtpException;
import com.Salaryfy.Interfaces.EmailVerificationService;
import com.Salaryfy.Repository.EmailVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Autowired EmailVerificationRepo emailVerificationRepo;

    @Override
    public void sendEmail(String otp, String email) {

        // Set the email message content
        String message = "Hello this is Aniket";

        // Set the password reset link
        String sendOtp = otp;

        // Set the email subject
        String subject = "Checking: confirmation";

        // Set the sender's email address
        String from = "b.aniket1414@gmail.com";

        // Set the recipient's email address
        String to = email;

        // Send the email using the sendEmail() method
        sendEmail(message, subject, to, from, sendOtp);

    }

    @Override
    public void saveEmail(String email, String otp) {

        EmailVerification emailVerification= new EmailVerification();
        emailVerification.setEmail(email);
        emailVerification.setOtp(otp);
        emailVerificationRepo.save(emailVerification);

    }

    @Override
    public String verifyOtp(String otp, String email) {
        EmailVerification emailVerification = emailVerificationRepo.findByEmail(email);

        if (emailVerification.getOtp().equals(otp)) {
           emailVerificationRepo.deleteById(emailVerification.getId());
            return "Verified";
        } else {
            throw new InvalidOtpException("Invalid OTP");
        }
    }

    private void sendEmail(String message, String subject, String to, String from, String sendOtp) {

        // SMTP server for Gmail
        String host = "smtp.gmail.com";

        // Getting the system properties
        Properties properties = System.getProperties();

        System.out.println(properties);

        // Setting important information to the properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Creating a session with the properties and an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Return the email address and password for authentication
                return new PasswordAuthentication("b.aniket1414@gmail.com", "egmqlowlfodymfzw");
            }

        });

        // Composing the email content
        String content = "OTP to verify Email   " + sendOtp;

        // Creating a MimeMessage object for the session
        MimeMessage m = new MimeMessage(session);

        try {
            // Setting the sender of the email
            m.setFrom(from);

            // Adding the recipient to the message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Adding the subject to the message
            m.setSubject(subject);

            // Adding the content to the message
            m.setText(content);

            // Sending the message
            Transport.send(m);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
