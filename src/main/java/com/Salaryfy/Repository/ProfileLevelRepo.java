package com.Salaryfy.Repository;

import com.Salaryfy.Entity.ProfileLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileLevelRepo extends JpaRepository<ProfileLevel,Integer> {
}
