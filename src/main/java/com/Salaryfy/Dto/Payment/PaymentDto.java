package com.Salaryfy.Dto.Payment;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDto {
    private String receipt;

    private String status;

    private Timestamp date;

    private Integer amount;

    private String paymentType;

    private String planName;

    private String orderId;

    private String paymentId;

    private Integer planId;

    private String userId;


}
