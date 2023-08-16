package com.Salaryfy.Repository;

import com.Salaryfy.Entity.JobfairQ1ans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobfairQ1ansRepo extends JpaRepository<JobfairQ1ans,Integer> {

    public Optional<JobfairQ1ans> findByUserId(Integer userId);
}
