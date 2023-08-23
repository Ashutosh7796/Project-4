package com.Salaryfy.Services;

import com.Salaryfy.Dto.Payment.PaymentDto;
import com.Salaryfy.Entity.Payment;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Interfaces.PaymentService;
import com.Salaryfy.Repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    @Override
    public List<PaymentDto> getAllPayments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Payment> allPayments = paymentRepo.findAll(pageable);

        List<PaymentDto> paymentDtoList = new ArrayList<>();

        for (Payment payment : allPayments) {
            PaymentDto paymentDto = convertToDto(payment);
            paymentDtoList.add(paymentDto);
        }
        if(paymentDtoList.size()<=0){throw new PageNotFoundException("page not found");}
        return paymentDtoList;

    }
    @Override
    public List<PaymentDto> getPaymentsByUserId(Integer user_id) {
        List<Payment> payments = paymentRepo.findByUserId(user_id);
        List<PaymentDto> paymentDtoList = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto paymentDto = convertToDto(payment);
            paymentDtoList.add(paymentDto);
        }

        return paymentDtoList;
    }


    private PaymentDto convertToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentType(paymentDto.getPaymentType());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setReceipt(payment.getReceipt());
        paymentDto.setDate(payment.getDate());
        paymentDto.setOrderId(payment.getOrderId());
        paymentDto.setStatus(payment.getStatus());
        paymentDto.setUserId(String.valueOf(payment.getUser().getUser_id()));
        paymentDto.setPlanId(payment.getPlan().getPlanId());
        paymentDto.setPlanName(payment.getPlan().getPlan());
        paymentDto.setPaymentId(payment.getPaymentId());
        return paymentDto;
    }
}
