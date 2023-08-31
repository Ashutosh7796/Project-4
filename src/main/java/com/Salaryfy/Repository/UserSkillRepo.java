package com.Salaryfy.Repository;

import com.Salaryfy.Entity.UserSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepo extends JpaRepository<UserSkills, Integer> {
}
