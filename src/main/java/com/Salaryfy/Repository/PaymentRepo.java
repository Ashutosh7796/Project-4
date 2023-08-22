package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository <Payment, Integer> {

    public Payment findByOrderId (String orderId);

    @Query("SELECT p FROM Payment p WHERE p.user.user_id = :user_id")
    List<Payment> findByUserId(@Param("user_id") Integer user_id);



}
