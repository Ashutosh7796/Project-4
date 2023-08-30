package com.Salaryfy.Entity;

import com.Salaryfy.Dto.UserSkills.UserSkillDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "UserSkills")
public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserSkillsId", nullable = false)
    private Integer UserSkillsId;

    @Column(name = "UserSkill", length = 250)
    private String UserSkill;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    public UserSkills(UserSkillDto userSkillDto) {
        this.UserSkill = userSkillDto.getUserSkill();
        this.user_id = userSkillDto.getUser_id();
    }
}
