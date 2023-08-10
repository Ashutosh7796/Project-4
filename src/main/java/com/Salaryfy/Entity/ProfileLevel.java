package com.Salaryfy.Entity;

import com.Salaryfy.Dto.ProfileLevelDto.ProfileLevelDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profile_level")
public class ProfileLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfileId", nullable = false)
    private Integer ProfileId;

    @Column(name = "highestLevelOfEdu", length = 250)
    private String highestLevelOfEdu;

    @Column(name = "Board", length = 250)
    private String board;

    @Column(name = "Stream", length = 250)
    private String stream;

    @Column(name = "Percentage", length = 45)
    private String percentage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userId", nullable = false)
    private User userUser;

    public ProfileLevel() {
    }

    public ProfileLevel(ProfileLevelDto profileLevelDto) {
        this.highestLevelOfEdu = profileLevelDto.getHighestLevelOfEdu();
        this.board = profileLevelDto.getBoard();
        this.stream = profileLevelDto.getStream();
        this.percentage = profileLevelDto.getPercentage();
    }
}