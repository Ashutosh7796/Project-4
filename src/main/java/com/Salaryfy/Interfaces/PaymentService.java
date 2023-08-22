package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.Payment.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> getAllPayments(int pageNo, int pageSize);

    List<PaymentDto> getPaymentsByUserId(Integer user_id);
}
