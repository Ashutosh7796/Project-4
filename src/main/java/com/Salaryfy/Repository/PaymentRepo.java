package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository <Payment, Integer> {

    public Payment findByOrderId (String orderId);
}
