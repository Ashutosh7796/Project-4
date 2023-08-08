package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job ,Integer> {

}
