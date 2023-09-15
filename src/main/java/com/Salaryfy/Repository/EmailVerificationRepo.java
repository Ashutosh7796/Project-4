package com.Salaryfy.Repository;

import com.Salaryfy.Entity.EmailVerification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailVerificationRepo extends JpaRepository<EmailVerification,Integer> {
    EmailVerification findByEmail(String email);

    List<EmailVerification> findByCreationTimeBefore(LocalDateTime dateTime);

    @Modifying
    @Transactional
    @Query("DELETE FROM EmailVerification WHERE (otp IS NOT NULL AND userOTP IS NOT NULL AND otp != userOTP) OR userOTP IS NULL OR userOTP = ''")
    void deleteUser();
}