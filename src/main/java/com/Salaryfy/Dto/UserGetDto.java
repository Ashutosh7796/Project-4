package com.Salaryfy.Dto;


import com.Salaryfy.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetDto {

    private String email;

    private String mobile_no;

    private String fullName;

    private LocalDate date;

    private String userProfileType;

    private String profilePhoto;

    private String subType;

    private String paymentValidity;

    private List Document;

    private Integer user_id;

    public UserGetDto(User user) {
        this.email = user.getEmail();
        this.mobile_no = user.getMobileNo();
        this.fullName = user.getFullName();
        this.date = user.getDate();
        this.userProfileType = user.getUserProfileType();
        this.profilePhoto = user.getProfilePhoto();
        this.subType = user.getSubType();
        this.paymentValidity = user.getPaymentValidity();
        this.Document= user.getDocuments();
    }
}

