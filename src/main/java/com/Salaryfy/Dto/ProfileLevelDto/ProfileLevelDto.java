package com.Salaryfy.Dto.ProfileLevelDto;

import com.Salaryfy.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileLevelDto {



    private String highestLevelOfEdu;


    private String board;


    private String stream;


    private String percentage;


}
