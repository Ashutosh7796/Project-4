package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Experiencedoc;
import com.Salaryfy.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienceDocRepo extends JpaRepository<Experiencedoc, Integer> {

    @Modifying
    @Query(value = "FROM Experiencedoc WHERE workExperience LIKE :years% ")
    List<Experiencedoc> findByExperiencedoc(@Param("years") String experience);

    List<Experiencedoc> findByCareerBreak(boolean status);
}
