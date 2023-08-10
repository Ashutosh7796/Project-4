package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Job;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job>{

    public List<Job> getJobsByStatus(boolean status);




}
