package com.Salaryfy.Entity;

import com.Salaryfy.Dto.UserSkills.UserSkillDto;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "UserId", nullable = false)
    private Integer UserId;

    public UserSkills(UserSkillDto userSkillDto) {
        this.UserSkill = userSkillDto.getUserSkill();
        this.UserId = userSkillDto.getUserId();
    }
}
