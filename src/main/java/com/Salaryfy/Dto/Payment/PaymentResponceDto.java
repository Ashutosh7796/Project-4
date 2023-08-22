package com.Salaryfy.Dto.Payment;

import com.Salaryfy.Dto.Job.InterviewScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponceDto {


        private String message;
        private List<PaymentDto> list;
        private String exception;

        public PaymentResponceDto(String message) {
            this.message = message;
        }
    }


