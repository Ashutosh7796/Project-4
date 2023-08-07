package com.Salaryfy.Dto.job.ProfileLevelDto;

import com.Salaryfy.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProfileLevelDto {



    private String highestLevelOfEdu;


    private String board;


    private String stream;


    private String percentage;

    private User userUser;
}
