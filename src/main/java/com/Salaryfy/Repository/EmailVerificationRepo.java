package com.Salaryfy.Repository;

import com.Salaryfy.Entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailVerificationRepo extends JpaRepository<EmailVerification,Integer> {
    EmailVerification findByEmail(String email);

    List<EmailVerification> findByCreationTimeBefore(LocalDateTime dateTime);
}