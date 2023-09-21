package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Job;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job>{

    public List<Job> getJobsByStatus(boolean status);

    Job findByJobId(Integer jobId);

    @Query("SELECT j FROM Job j " +
            "WHERE LOWER(j.companyName) LIKE %:keyword% " +
            "OR LOWER(j.location) LIKE %:keyword% " +
            "OR LOWER(j.postName) LIKE %:keyword%")
    public List<Job> searchJobsByKeyword(@Param("keyword") String keyword);


    @Query(value = "SELECT location FROM salaryfy.jobs;",nativeQuery = true)
    Set<String> findLocation();
    @Query(value = "SELECT job_type FROM salaryfy.jobs;",nativeQuery = true)
    Set<String> findjobType();

    @Query(value = "SELECT company_name FROM salaryfy.jobs;",nativeQuery = true)
    Set<String> findcompanyName();
}
