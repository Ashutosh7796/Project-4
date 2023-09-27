package com.Salaryfy.Services;

import com.Salaryfy.Entity.EmailVerification;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.EmptyFiledException;
import com.Salaryfy.Exception.InvalidOtpException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Interfaces.EmailVerificationService;
import com.Salaryfy.Repository.EmailVerificationRepo;
import com.Salaryfy.Repository.OtpExpiredException;
import com.Salaryfy.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Autowired EmailVerificationRepo emailVerificationRepo;

    @Autowired
    UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String emailUsername;

    @Value("${spring.mail.password}")
    private String emailPassword;
    @Value("${spring.mail.host}")
    private String emailHost;
    @Value("${spring.mail.port}")
    private String emailPort;
    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private String emailSmtpSslEnable;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String emailSmtpAuth;

    @Override
    public void sendEmail(String otp, String email) {
        if (email != null) {

            User byEmail = userRepository.findByEmail(email);

            if (byEmail == null) {

                String emailTemplate= loadEmailTemplate("otp_email_template.html");
                emailTemplate=emailTemplate.replace("{{otp}}",otp);

                // Set the email subject
                String subject = "OTP Verification";

                // Set the sender's email address
                String from = emailUsername;

                // Set the recipient's email address
                String to = email;

                // Send the email using the sendEmail() method
                sendEmail(emailTemplate, subject, to, from);
            } else {
                throw new UserAlreadyExistException("User Already present with this email");
            }
        }else {
            throw new EmptyFiledException("Email filed is empty");
        }
    }

    @Override
    public String saveEmail(String email, String otp,LocalDateTime localDateTime) {
        if (email != null) {
        EmailVerification emailVerifications = emailVerificationRepo.findByEmail(email);
        if (emailVerifications != null){
            // EmailVerification emailVerification= new EmailVerification();
            emailVerifications.setEmail(email);
            emailVerifications.setOtp(otp);
            emailVerifications.setStatus("Not verified");
            emailVerifications.setCreationTime(localDateTime);
            emailVerificationRepo.save(emailVerifications);
            return "Email saved";
        }else {
                EmailVerification emailVerification = new EmailVerification();
                emailVerification.setEmail(email);
                emailVerification.setOtp(otp);
                emailVerification.setStatus("Not verified");
                emailVerification.setCreationTime(localDateTime);
                emailVerificationRepo.save(emailVerification);
                return "Email saved";
            }
        }else {
            throw new EmptyFiledException("Fill the field");
        }
    }

    @Override
    public String verifyOtp(String otp, String email) {
        EmailVerification emailVerification = emailVerificationRepo.findByEmail(email);
        if (emailVerification == null) {
            throw new InvalidOtpException("Invalid OTP");
        } else {
            if (emailVerification.getOtp().equals(otp)) {
                LocalDateTime creationTime = emailVerification.getCreationTime();
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(creationTime, currentTime);

                if (duration.toMinutes() <= 3) {
                    emailVerification.setStatus("Verified");
                    emailVerificationRepo.save(emailVerification);
                    return "Verified";
                } else {
                    throw new OtpExpiredException("OTP has expired");
                }
            } else {
                throw new InvalidOtpException("Invalid OTP");
            }
        }
    }

    @Override
    @Transactional
    public void cleanupExpiredOTP() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(3);
        emailVerificationRepo.deleteByStatusAndCreationTimeBefore("Not Verified", expirationTime);
    }

    private void sendEmail(String message, String subject, String to, String from) {

        if (to != null && !to.isEmpty()) {
            // SMTP server for Gmail
            String host = emailHost;

            // Getting the system properties
            Properties properties = System.getProperties();

            System.out.println(properties);

            // Setting important information to the properties object
            properties.put("mail.smtp.host", emailHost);
            properties.put("mail.smtp.port", emailPort);
            properties.put("mail.smtp.ssl.enable", emailSmtpSslEnable);
            properties.put("mail.smtp.auth", emailSmtpAuth);

            // Creating a session with the properties and an authenticator
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // Return the email address and password for authentication
                    return new PasswordAuthentication(emailUsername, emailPassword);
                }

            });

            // Composing the email content
//            String content = "OTP to verify Email   " + sendOtp;

            // Creating a MimeMessage object for the session
            MimeMessage m = new MimeMessage(session);

            try {
                // Setting the sender of the email
                m.setFrom(from);

                // Adding the recipient to the message
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Adding the subject to the message
                m.setSubject(subject);

                m.setContent(message,"text/html");

                // Adding the content to the message
//                m.setText(content);

                // Sending the message
                Transport.send(m);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else {
            throw new EmptyFiledException("fill the field");
        }
    }
    private
    String loadEmailTemplate(String templateFileName) {
        try
        { InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateFileName);
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    return reader.lines().collect(Collectors.joining(System.lineSeparator())); } }
            else
            {
                throw new IOException("Template file not found: " + templateFileName); }
        } catch
        (IOException e) {
            e.printStackTrace();throw new RuntimeException("Error loading email template");
        }
    }
}