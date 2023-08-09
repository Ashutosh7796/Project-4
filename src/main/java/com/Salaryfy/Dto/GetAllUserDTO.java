package com.Salaryfy.Dto;

import com.Salaryfy.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserDTO {


    private String email;

    private String mobile_no;

    private String fullName;

    private LocalDate date;

    private String userProfileType;

    private String profilePhoto;

    private String subType;

    private String paymentValidity;

    private Integer user_id;

    public GetAllUserDTO(User user) {
        this.email = user.getEmail();
        this.mobile_no = user.getMobileNo();
        this.fullName = user.getFullName();
        this.date = user.getDate();
        this.userProfileType = user.getUserProfileType();
        this.profilePhoto = user.getProfilePhoto();
        this.subType = user.getSubType();
        this.paymentValidity = user.getPaymentValidity();

    }
}
