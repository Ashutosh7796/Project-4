package com.Salaryfy.Repository;

import com.Salaryfy.Entity.JobfairQ2ans;
import com.Salaryfy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobfairQ2ansRepo extends JpaRepository<JobfairQ2ans,Integer> {

}
