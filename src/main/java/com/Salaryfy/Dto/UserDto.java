package com.Salaryfy.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;

    private String password;

    private String mobile_no;

    private String role;

    private String fullName;

    private LocalDate date;

    private String userProfileType;

    private String profilePhoto;

    private String subType;

    private String paymentValidity;

    private String Document;

    private Integer user_id;


}
