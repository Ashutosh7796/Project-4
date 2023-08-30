package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository<T, I extends Number> extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job>{

    public List<Job> getJobsByStatus(boolean status);

    @Query("SELECT j FROM Job j " +
            "WHERE LOWER(j.companyName) LIKE %:keyword% " +
            "OR LOWER(j.location) LIKE %:keyword% " +
            "OR LOWER(j.postName) LIKE %:keyword%")
    public List<Job> searchJobsByKeyword(@Param("keyword") String keyword);

    List<T> findAll02(@Nullable Specification<T> spec);


}
