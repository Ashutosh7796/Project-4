package com.Salaryfy.Dto.ProfileLevelDto;

import com.Salaryfy.Entity.ProfileLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProfileLevelDto {



    public String highestLevelOfEdu;


    public String board;


    public String stream;

    public String percentage;

    public Integer UserId;

    public ProfileLevelDto() {
    }

    public ProfileLevelDto(ProfileLevel profileLevel ) {
        this.highestLevelOfEdu = profileLevel.getHighestLevelOfEdu();
        this.board = profileLevel.getBoard();
        this.stream = profileLevel.getStream();
        this.percentage = profileLevel.getPercentage();
        this.UserId = profileLevel.getUserUser().getUser_id();
    }
}
