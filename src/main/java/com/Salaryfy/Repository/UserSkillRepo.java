package com.Salaryfy.Repository;

import com.Salaryfy.Entity.UserSkills;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillRepo extends JobRepository<UserSkills, Integer> {
}
