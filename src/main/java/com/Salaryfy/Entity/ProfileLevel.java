package com.Salaryfy.Entity;

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
    private Integer id;

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

}