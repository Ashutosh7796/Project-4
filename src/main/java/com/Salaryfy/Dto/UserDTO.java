package com.Salaryfy.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;

    private String password;

    private Integer mobile_no;

    private String role;

    private String fullName;

    private LocalTime time;

    private String userProfileType;

    private String profilePhoto;

    private String subType;

    private String paymentValidity;

    private String Document;


}
